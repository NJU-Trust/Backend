package nju.trust.service.admin;

import nju.trust.dao.admin.*;
import nju.trust.dao.admin.UserEvidenceDao.UserEvidenceRepository;
import nju.trust.dao.record.InvestmentRecordRepository;
import nju.trust.dao.record.RepaymentRecordRepository;
import nju.trust.dao.target.LargeTargetRepository;
import nju.trust.dao.target.SmallTargetRepository;
import nju.trust.dao.target.TargetRepository;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.ApproveResult;
import nju.trust.entity.record.InvestmentRecord;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.record.UserEvidence.BaseUserEvidence;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.target.*;
import nju.trust.entity.UserType;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.admin.*;
import nju.trust.payloads.user.UserSimpleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 161250127
 * @Description: 管理员模块的逻辑层实现
 * @Date: 2018/8/26
 */
@Service
public class AdminServiceImpl implements AdminService {
    private LoanStateCheckUtil calUtil;
    private AdminUserRepository adminUserRepository;
    private BaseTargetRepository baseTargetRepository;
    private AdminInvestmentRecordRepository adminInvestmentRecordRepository;
    private SmallTargetRepository smallTargetRepository;
    private LargeTargetRepository largeTargetRepository;
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    private RepaymentRepository repaymentRepository;
    private UserEvidenceRepository userEvidenceRecordRepository;
    private InvestmentRecordRepository investmentRecordRepository;
    private RepaymentRecordRepository repaymentRecordRepository;
    private ScoreCalUtil scoreCalUtil;
    @Autowired
    public AdminServiceImpl(LoanStateCheckUtil calUtil, AdminUserRepository adminUserRepository, BaseTargetRepository baseTargetRepository, AdminInvestmentRecordRepository adminInvestmentRecordRepository, SmallTargetRepository smallTargetRepository, LargeTargetRepository largeTargetRepository, UserInfoCheckRecordRepository userInfoCheckRecordRepository, RepaymentRepository repaymentRepository, UserEvidenceRepository userEvidenceRecordRepository, InvestmentRecordRepository investmentRecordRepository, RepaymentRecordRepository repaymentRecordRepository, ScoreCalUtil scoreCalUtil) {
        this.calUtil = calUtil;
        this.adminUserRepository = adminUserRepository;
        this.baseTargetRepository = baseTargetRepository;
        this.adminInvestmentRecordRepository = adminInvestmentRecordRepository;
        this.smallTargetRepository = smallTargetRepository;
        this.largeTargetRepository = largeTargetRepository;
        this.userInfoCheckRecordRepository = userInfoCheckRecordRepository;
        this.repaymentRepository = repaymentRepository;
        this.userEvidenceRecordRepository = userEvidenceRecordRepository;
        this.investmentRecordRepository = investmentRecordRepository;
        this.repaymentRecordRepository = repaymentRecordRepository;
        this.scoreCalUtil = scoreCalUtil;
    }

    @Override
    public int getUserListLen(String keyword, UserType type) {
        return getUserSimpleList(keyword, type).size();
    }

    /**
     * 查找所有用户的概要信息
     * @param keyword 关键字
     * @param type 用户类别（借款用户：无借款用户、待还款用户、逾期用户；投资用户）
     * @return List<UserSimpleInfo>
     */
    @Override
    public List<UserSimpleInfo> getUserList(String keyword, UserType type) {
        // 通过模糊查询、用户类别筛选用户
        List<UserSimpleInfo> infoList = getUserSimpleList(keyword, type);

        return infoList;
        // 分页
        /*int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<UserSimpleInfo> infoList2 = new ArrayList<>();
        for(int i = pageNumber*pageSize ; i < infoList.size() && i < (pageNumber+1)*pageSize ; i++) {
            infoList2.add(infoList.get(i));
        }

        return infoList2;*/
    }
    private List<UserSimpleInfo> getUserSimpleList(String keyword, UserType type) {
        List<String> usernames;
        // 通过模糊查询查找用户姓名
        if(keyword == null || keyword.equals("")) { // 关键字不存在，返回所有用户姓名
            usernames = adminUserRepository.getAllName();
        }else {
            usernames = adminUserRepository.getNamesByKeyword(keyword);
        }
        usernames.remove("admin");

        // 通过用户类别筛选用户
        List<UserSimpleInfo> infoList;
        if(type == null) {// 不区分usertype
            infoList = getLoanUserInfo(usernames);
        }else {
            switch (type) {
                case NOLOAN:    // 无借款用户
                    infoList = getLoanUserInfo(usernames, type);
                    break;
                case HAVELOAN:  // 待还款用户
                    infoList = getLoanUserInfo(usernames, type);
                    break;
                case OVERDUE:   // 逾期用户
                    infoList = getOverdueUserInfo(usernames);
                    break;
            /*case INVESTMENT:// 投资用户
                infoList = getInvestorInfo(usernames);
                break;*/
                default:        // 不区分usertype
                    infoList = getLoanUserInfo(usernames);
                    break;
            }
        }

        if(infoList == null) {
            return new ArrayList<>();
        }

        return infoList;
    }
    // 查找用户是否欠款，返回未欠款/欠款用户的UserSimpleInfo
    private List<UserSimpleInfo> getLoanUserInfo(List<String> usernames, UserType type) {
        List<UserSimpleInfo> list = new ArrayList<>();
        for (String username : usernames) {
            calUtil.setUsername(username);
            if ((calUtil.checkUserType().equals(type))) {   // 是所求用户
                if(adminUserRepository.existsById(username)) {
                    User user = adminUserRepository.findById(username).get();
                    UserSimpleInfo userSimpleInfo = new UserSimpleInfo(user, type);
                    list.add(userSimpleInfo);
                }
            }
        }
        return list;
    }
    // 查找逾期用户（逾期金额>0）
    private List<UserSimpleInfo> getOverdueUserInfo(List<String> usernames) {
        int i = 0;
        while (i < usernames.size()) {
            String username = usernames.get(i);
            calUtil.setUsername(username);
            if(!calUtil.checkUserType().equals(UserType.OVERDUE)) {// 不是逾期用户
                usernames.remove(i);
            }else {
                i++;
            }
        }
        List<User> users = (List<User>)adminUserRepository.findAllById(usernames);
        return getSimpleInfo(users, UserType.OVERDUE);
    }
    // 不区分用户类别时查看用户概要信息
    private List<UserSimpleInfo> getLoanUserInfo(List<String> usernames) {
        List<UserSimpleInfo> list = new ArrayList<>();
        for (String username : usernames) {
            calUtil.setUsername(username);
            UserType type = calUtil.checkUserType();
            User user = adminUserRepository.findById(username).get();
            UserSimpleInfo userSimpleInfo = new UserSimpleInfo(user, type);
            list.add(userSimpleInfo);
        }
        return list;
    }
    private List<UserSimpleInfo> getSimpleInfo(List<User> users, UserType type) {
        List<UserSimpleInfo> infos = new ArrayList<>();
        for(User user : users) {
            UserSimpleInfo info = new UserSimpleInfo(user, type);
            infos.add(info);
        }
        return infos;
    }
    // 去除管理员
    private List<UserSimpleInfo> removeAdmin(List<UserSimpleInfo> infoList) {
        int j = 0;
        while(j < infoList.size()) {
            UserSimpleInfo info = infoList.get(j);
            if(info.getUsername().toLowerCase().equals("admin")) {
                infoList.remove(j);
            }else {
                j++;
            }
        }
        return infoList;
    }

    /**
     * 查看项目
     * @param state 项目状态(招标中,审核中,还款中,已还款)
     * @param type  项目类型（小额拆借类、学习培训类）
     * @return 项目的概要信息列表
     */
    @Override
    public List<TargetAdminBriefInfo> seeTarget(TargetState state, TargetType type) {
        List<BaseTarget> records;

        // 通过状态查看标的
        if(state != null && type != null) {
            records = baseTargetRepository.findDistinctByTargetStateAndTargetType(state, type);
        }else if(state == null){
            records = (List<BaseTarget>) baseTargetRepository.findAll();
            records = getTargetInfoByType(records, type);
        }else {
            records = baseTargetRepository.findDistinctByTargetState(state);
            records = getTargetInfoByType(records, type);
        }
        if(records == null || records.size() == 0) {
            return new ArrayList<>();
        }

        // BaseTarget转TargetAdminBriefInfo
        List<TargetAdminBriefInfo> infos = baseTargetsToBriefInfos(records);

        return infos;
        /*// 分页
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<TargetAdminBriefInfo> infoList = new ArrayList<>();
        for(int i = pageNumber*pageSize ; i < infos.size() && i < (pageNumber+1)*pageSize ; i++) {
            infoList.add(infos.get(i));
        }

        return infoList;*/
    }
    // 通过type对target进行筛选并分页
    private List<BaseTarget> getTargetInfoByType(List<BaseTarget> records, TargetType type) {
        if(type != null) {  // 通过type对target进行筛选
            int i = 0;
            while(i < records.size()) {
                if(!records.get(i).getTargetType().equals(type)) {
                    records.remove(i);
                }else {
                    i++;
                }
            }
        }
        return records;
    }
    // 将BaseTarget列表转为TargetAdminBriefInfo列表
    private List<TargetAdminBriefInfo> baseTargetsToBriefInfos(List<BaseTarget> records) {
        List<TargetAdminBriefInfo> infos = new ArrayList<>();
        for(BaseTarget baseTarget : records){
            TargetAdminBriefInfo info = baseTargetToBriefInfo(baseTarget);
            infos.add(info);
        }
        return infos;
    }
    // 将BaseTarget转为TargetAdminBriefInfo
    private TargetAdminBriefInfo baseTargetToBriefInfo(BaseTarget baseTarget) {
        Long targetId = baseTarget.getId();
        List<User> investors = adminInvestmentRecordRepository.findUserById(targetId);
        List<String> nameList = new ArrayList();
        for(User user : investors) {
            String username = user.getUsername();
            if(!nameList.contains(username)) {
                nameList.add(username);
            }
        }

        String classification = "";
        if(baseTarget.getTargetType() == TargetType.LARGE) {
            LargeTarget largeTarget = largeTargetRepository.findById(baseTarget.getId()).get();
            classification = largeTarget.getClassification().getStr();
        }
        if(baseTarget.getTargetType() == TargetType.SMALL) {
            SmallTarget smallTarget = smallTargetRepository.findById(baseTarget.getId()).get();
            classification = smallTarget.getClassification().getStr();
        }

        return new TargetAdminBriefInfo(baseTarget, nameList, classification);
    }

    /**
     * 查看项目信息
     * @param id 项目编号
     * @return 项目的详细信息
     */
    @Override
    public TargetAdminDetailInfo seeTarget(Long id) {
        if(repaymentRepository.existsByTargetId(id) && baseTargetRepository.existsById(id)) {
            System.out.println("repayment exists");
            BaseTarget baseTarget = baseTargetRepository.findById(id).get();
            Repayment repayment = repaymentRepository.findFirstByTargetId(id);
            return new TargetAdminDetailInfo(baseTarget, repayment);
        }else {
            return new TargetAdminDetailInfo();
        }
    }

    /**
     * 统计基础数据
     * @return 当日
     * 总额：交易总额、交易总笔数、借款人数量、投资人数量
     * 人均统计：人均累计借款额度、笔均借款额度、人均累计投资额度
     * 其他统计：最大单户借款余额占比、最大10户借款余额占比、平均满标时间
     */
    @Override
    public BaseStatistics getBaseStatistics() {
        LocalDate date = LocalDate.now();
        List<BaseTarget> targets = baseTargetRepository.findDistinctByStartTime(date);

        BaseStatistics baseStatistics = new BaseStatistics();

        double dealMoneySum = getDealMoneySum(targets);
        baseStatistics.setDealMoneySum(dealMoneySum);
        int dealNum = getDealNum(targets);
        baseStatistics.setDealNum(dealNum);
        int borrowerNum = getBorrowerNum(targets);
        baseStatistics.setBorrowerNum(borrowerNum);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = date.toString();
        LocalDateTime time1 = LocalDateTime.parse(s + " 00:00:00", df);
        LocalDateTime time2 = LocalDateTime.parse(s + " 23:59:59", df);
        List<InvestmentRecord> investmentRecords = investmentRecordRepository.findDistinctByTimeBetween(time1, time2);

        int investorNum = getInvestorNum(investmentRecords);
        baseStatistics.setInvestorNum(investorNum);

        targets = (List<BaseTarget>)baseTargetRepository.findAll();
        borrowerNum = getBorrowerNum(targets);
        investmentRecords = (List<InvestmentRecord>)investmentRecordRepository.findAll();
        investorNum = getInvestorNum(investmentRecords);

        double loanPerPerson = getLoanPerPerson(targets, borrowerNum);
        baseStatistics.setLoanPerPerson(loanPerPerson);

        double loanPerTarget = getLoanPerTarget(targets);
        baseStatistics.setLoanPerTarget(loanPerTarget);

        double investmentPerPerson = getInvestmentPerPerson(investmentRecords, investorNum);
        baseStatistics.setInvestmentPerPerson(investmentPerPerson);

        ArrayList<String> top10 = getMost10Loan();

        if(top10 != null && top10.size() > 0) {
            double mostLoanPersonRate = getLoanPersonRate(top10.get(0));
            baseStatistics.setMostLoanPersonRate(mostLoanPersonRate);

            if(top10.size() >= 10) {
                double most10LoanPersonRate = getMost10LoanPersonRate(top10.subList(0, 10));
                baseStatistics.setMost10LoanPersonRate(most10LoanPersonRate);
            }
        }

        double averageGoingTime = getAverageGoingTime(targets);
        baseStatistics.setAverageGoingTime(averageGoingTime);

        return baseStatistics;
    }
    // 交易总额
    private double getDealMoneySum(List<BaseTarget> targets) {
        double sum = 0;
        for(BaseTarget target : targets) {
            sum = sum + target.getMoney();
        }
        return sum;
    }
    // 交易总笔数
    private int getDealNum(List<BaseTarget> targets) {
        return targets.size();
    }
    // 借款人数量
    private int getBorrowerNum(List<BaseTarget> targets) {
        List<String> names = new ArrayList<>();
        for(BaseTarget target : targets) {
            String name = target.getUser().getUsername();
            if(!names.contains(name)) {
                names.add(name);
            }
        }
        return names.size();
    }
    // 投资人数量
    private int getInvestorNum(List<InvestmentRecord> investmentRecords) {
        List<String> names = new ArrayList<>();
        for(InvestmentRecord investmentRecord : investmentRecords) {
            String username = investmentRecord.getUser().getUsername();
            if(!names.contains(username)) {
                names.add(username);
            }
        }
        return names.size();
    }
    // 人均累计借款额度
    private double getLoanPerPerson(List<BaseTarget> targets, int borrowerNum) {
        if(borrowerNum <= 0) {
            return -1;
        }
        double money = 0;
        for(BaseTarget target : targets) {
            money = money + target.getMoney();
        }
        return money/borrowerNum;
    }
    // 笔均借款额度
    private double getLoanPerTarget(List<BaseTarget> targets) {
        if(targets == null || targets.size() == 0) {
            return -1;
        }
        double money = 0;
        for(BaseTarget target : targets) {
            money = money + target.getMoney();
        }
        return money/targets.size();
    }
    // 人均累计投资额度
    private double getInvestmentPerPerson(List<InvestmentRecord> investmentRecords, int investorNum) {
        if(investorNum <= 0) {
            return -1;
        }
        double money = 0;
        for(InvestmentRecord investmentRecord : investmentRecords) {
            money = money + investmentRecord.getInvestedMoney();
        }
        return money/investorNum;
    }
    // 计算借款余额占比  借款余额比=当前应还但还未偿还的总额 / 他总共借的钱
    private double getLoanPersonRate(String username) {
        double toRepay = getToRepay(username);
        double totalLoan = getTotalLoan(username);

        if(totalLoan > 0) {
            return toRepay/totalLoan;
        }else {
            return -1;
        }
    }
    // 当前应还但还未偿还的总额
    private double getToRepay(String username) {
        List<Repayment> repayments = repaymentRepository.findAllByUserUsername(username);
        double sum = 0;
        for(Repayment repayment : repayments) {
            sum = sum + repayment.getRemainingAmount();
        }
        return sum;
    }
    // 总共借的钱
    private double getTotalLoan(String username) {
        List<BaseTarget> targets = baseTargetRepository.findDistinctByUserUsername(username);
        double sum = 0;
        for(BaseTarget target : targets) {
            sum = sum + target.getMoney();
        }
        return sum;
    }
    // 最大10户借款余额占比
    private double getMost10LoanPersonRate(List<String> top10) {
        double toRepay = 0, totalLoan = 0;
        for(String name : top10) {
            toRepay = toRepay + getToRepay(name);
            totalLoan = totalLoan + getTotalLoan(name);
        }

        if(totalLoan > 0) {
            return toRepay/totalLoan;
        }else {
            return -1;
        }
    }
    // 平均满标时间（以天为单位）
    private double getAverageGoingTime(List<BaseTarget> targets) {
        double sum = 0;
        int num = 0;

        for(BaseTarget target : targets) {
            Long id = target.getId();
            if(repaymentRepository.existsByTargetId(id)) {
                num++;
                Repayment repayment = repaymentRepository.findFirstByTargetId(id);
                LocalDate startDate = target.getStartTime();
                LocalDate endDate = repayment.getStartDate();
                Period period = Period.between(startDate, endDate);
                sum = sum + period.getDays();
            }
        }

        if(num <= 0) {
            return -1;
        }else {
            return sum/num;
        }
    }
    // 得到最大n户用户名（n<=10） 最大用户就是在平台上借款总额最多的用户
    private ArrayList<String> getMost10Loan() {
        List<BaseTarget> allTargets = (List<BaseTarget>)baseTargetRepository.findAll();

        List<UserLoanRelation> relations = new ArrayList<>();
        for(BaseTarget target : allTargets) {
            String username = target.getUser().getUsername();
            double money = target.getMoney();
            UserLoanRelation relation = new UserLoanRelation(username, money);

            int index = relations.indexOf(relation);
            if(index < 0) {
                relations.add(relation);
            }else {
                relations.get(index).plus(relation);
            }
        }

        return getTop10(relations);
    }
    // 得到最大10户username（若不足10户则全部返回）
    private ArrayList<String> getTop10(List<UserLoanRelation> relations) {
        ArrayList<String> top10 = new ArrayList<>();
        final int NUM = 10;
        Collections.sort(relations);
        int len = relations.size() < NUM ? relations.size() : NUM;
        for(int i = 0 ; i < len ; i++) {
            top10.add(relations.get(i).getUsername());
        }
        return top10;
    }

    /**
     * 统计违约信息
     * @return 当月
     * 累计违约率、逾期项目数、项目逾期率、近三月项目逾期率、借款逾期金额
     * 待偿金额、借贷金额逾期率、借贷坏账率、客户投诉情况
     */
    @Override
    public BreakContractStatistics getBreakContractStatistics() {
        BreakContractStatistics breakContractStatistics = new BreakContractStatistics();

        LocalDate now = LocalDate.now();

        LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = now.minusDays(1);
        List<Long> overdueProjects = getOverdueProjects(start, end);
        List<Long> toRepayProjects = getToRepayProjects(start, end);

        // 累计违约率
        double defaultRate = getDefaultRate();
        breakContractStatistics.setDefaultRate(defaultRate);

        // 逾期项目数
        int overdueProgramNum = overdueProjects.size();
        breakContractStatistics.setOverdueProgramNum(overdueProgramNum);

        // 项目逾期率
        double overdueProgramRate = getOverdueProgramRate(overdueProjects, toRepayProjects);//overdueProjects.size()/(double)toRepayProjects.size();
        breakContractStatistics.setOverdueProgramRate(overdueProgramRate);

        // 近三月项目逾期率
        LocalDate start3 = start.minusMonths(2);
        List<Long> overdueProjects3 = getOverdueProjects(start3, end);
        List<Long> toRepayProjects3 = getToRepayProjects(start3, end);
        double overdueProgramRate3 = getOverdueProgramRate(overdueProjects3, toRepayProjects3);
        breakContractStatistics.setOverdueProgramRate3(overdueProgramRate3);

        // 借款逾期金额
        double overdueMoneySum = getOverdueMoneySum(overdueProjects, start, end);
        breakContractStatistics.setOverdueMoneySum(overdueMoneySum);

        // 待偿金额
        double toPay = getToPay();
        breakContractStatistics.setToPay(toPay);

        // 借贷金额逾期率
        double overdueMoneyRate = getOverdueMoneyRate(overdueMoneySum);
        breakContractStatistics.setOverdueMoneyRate(overdueMoneyRate);

        // 借贷坏账率
        double badDebtRate = getBadDebtRate();
        breakContractStatistics.setBadDebtRate(badDebtRate);

        return breakContractStatistics;
    }
    // 借贷坏账率
    private double getBadDebtRate() {
        double violateSum = getTotalViolateSum();
        double totalSum = getTotalSum();

        if(totalSum > 0) {
            return violateSum/totalSum;
        }else {
            return -1;
        }
    }
    // 自平台开始运营后30天未还款的还款额
    private double getTotalViolateSum() {
        List<RepaymentRecord> repaymentRecords = repaymentRecordRepository.findAll();
        double sum = 0;

        for(RepaymentRecord repaymentRecord : repaymentRecords) {
            if(isViolate(repaymentRecord)) {
                sum = sum + repaymentRecord.getSum();
            }
        }

        return sum;
    }
    // 自平台开始运营后的项目总金额
    private double getTotalSum() {
        List<RepaymentRecord> repaymentRecords = repaymentRecordRepository.findAll();
        double sum = 0;

        for(RepaymentRecord repaymentRecord : repaymentRecords) {
            sum = sum + repaymentRecord.getSum();
        }

        return sum;
    }
    // 累计违约率
    private double getDefaultRate() {
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());
        List<Long> goingTargets = getToRepayProjects(start, end);

        int violateNum = getViolateNum();
        int goingNum = goingTargets.size();

        if(goingNum != 0) {
            return violateNum/(double)goingNum;
        }else {
            return -1;
        }
    }
    // 得到本月违约项目数量
    private int getViolateNum() {
        List<Long> violateTargets = new ArrayList<>();

        LocalDate now = LocalDate.now();
        LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth()).minusDays(30);
        LocalDate end = now.minusDays(30);

        List<RepaymentRecord> repaymentRecords = repaymentRecordRepository.findDistinctByReturnDateBetween(start, end);
        for(RepaymentRecord repaymentRecord : repaymentRecords) {
            if(isViolate(repaymentRecord)) {
                long id = repaymentRecord.getTarget().getId();
                if(!violateTargets.contains(id)) {
                    violateTargets.add(id);
                }
            }
        }

        return violateTargets.size();
    }
    public boolean isViolate(RepaymentRecord repaymentRecord) {
        LocalDate returnDate = repaymentRecord.getReturnDate();
        LocalDate actualReturnDate = repaymentRecord.getActualRepayDate();

        if(actualReturnDate == null) {
            actualReturnDate = LocalDate.now();
        }

        return actualReturnDate.minusDays(30).isAfter(returnDate);
    }
    // 借贷金额逾期率
    private double getOverdueMoneyRate(double overdueMoneySum) {
        double repaySum = getRepaySum();
        if(repaySum > 0) {
            return overdueMoneySum/repaySum;
        }
        return -1;
    }
    // 本月总借款金额
    private double getRepaySum() {
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());

        List<RepaymentRecord> repaymentRecords = repaymentRecordRepository.findDistinctByReturnDateBetween(start, end);
        double sum = 0;
        for(RepaymentRecord repaymentRecord : repaymentRecords) {
            sum = sum + repaymentRecord.getSum();
        }
        return sum;
    }
    // 待偿金额
    private double getToPay() {
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());

        List<Long> toRepayProjects = getToRepayProjects(start, end);

        if(toRepayProjects == null) {
            return -1;
        }

        double sum = 0;
        for(Long targetId : toRepayProjects) {
            List<RepaymentRecord> repaymentRecords = repaymentRecordRepository.findDistinctByTargetIdAndReturnDateBetween(targetId, start, end);
            for(RepaymentRecord repaymentRecord : repaymentRecords) {
                if(repaymentRecord.getReturnDate().isBefore(now) && repaymentRecord.getActualRepayDate()==null) {
                    sum = sum + repaymentRecord.getSum();
                }
            }
        }
        return sum;
    }
    // 项目逾期率
    private double getOverdueProgramRate(List<Long> overdueProjects, List<Long> toRepayProjects) {
        if(toRepayProjects == null || toRepayProjects.size() == 0) {
            return -1;
        }else {
            return overdueProjects.size() / (double)toRepayProjects.size();
        }
    }
    // 借款逾期金额
    private double getOverdueMoneySum(List<Long> overdueProjects, LocalDate start, LocalDate end) {
        if(overdueProjects != null) {
            double sum = 0;
            for(int i = 0 ; i < overdueProjects.size() ; i++) {
                long targetId = overdueProjects.get(i);
                List<RepaymentRecord> repaymentRecords = repaymentRecordRepository.findDistinctByTargetIdAndReturnDateBetween(targetId, start, end);
                for(RepaymentRecord record : repaymentRecords) {
                    if(record.isOverdue()) {
                        sum = sum + record.getSum();
                    }
                }
            }
            return sum;
        }
        return -1;
    }
    // 得到某一时间段内的逾期项目（闭区间）
    private List<Long> getOverdueProjects(LocalDate start, LocalDate end) {
        List<Long> targets = new ArrayList<>();

        List<RepaymentRecord> repaymentRecords = repaymentRecordRepository.findDistinctByReturnDateBetween(start, end);
        for(RepaymentRecord repaymentRecord : repaymentRecords) {
            LocalDate returnDate = repaymentRecord.getReturnDate();
            LocalDate actualDate = repaymentRecord.getActualRepayDate();
            if(actualDate == null || actualDate.isAfter(returnDate)) {
                Long targetId = repaymentRecord.getTarget().getId();
                if(!targets.contains(targetId)) {
                    targets.add(targetId);
                }
            }
        }

        return targets;
    }
    // 得到某一时间段内需还款的项目编号（闭区间）
    private List<Long> getToRepayProjects(LocalDate start, LocalDate end) {
        List<Long> targets = new ArrayList<>();

        List<RepaymentRecord> records = repaymentRecordRepository.findDistinctByReturnDateBetween(start, end);

        for(RepaymentRecord record : records) {
            long targetsId = record.getTarget().getId();
            if(!targets.contains(targetsId)) {
                targets.add(targetsId);
            }
        }

        // 很久以前应该还款，直到现在仍未还款的项目
        List<RepaymentRecord> records1 = repaymentRecordRepository.findDistinctByReturnDateLessThan(start);
        for(RepaymentRecord record : records1) {
            long targetId = record.getTarget().getId();
            if(record.getActualRepayDate() == null && !targets.contains(targetId)) {
                targets.add(targetId);
            }
        }

        return targets;
    }

    /**
     * 用户审核时得到待审核用户及其状态的列表
     * 优先级：UPDATE > SUBMIT 时间早 > 时间晚
     * @return List<UserStateList>
     */
    @Override
    public List<UserStateList> getUserStateList() {
        List<UserInfoCheckRecord> records = userInfoCheckRecordRepository.findByCheckState(CheckState.UPDATE);
        records.addAll(userInfoCheckRecordRepository.findByCheckState(CheckState.ONGING));

        List<UserStateList> list = getList(records);

        Collections.sort(list);

        return list;

/*        // 分页
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<UserStateList> infoList = new ArrayList<>();
        for(int i = pageNumber*pageSize ; i < list.size() && i < (pageNumber+1)*pageSize ; i++) {
            infoList.add(list.get(i));
        }
        return infoList;*/
    }

    /**
     * 返回用户的待审核条目
     * @param username 用户名
     * @return 待审核条目信息
     */
    @Override
    public UserCheckResponse getUserCheckItems(String username) {
        List<UserCheckItem> toApprove = new ArrayList<>();
        List<UserCheckItem> haveApproved = new ArrayList<>();

        List<UserInfoCheckRecord> records = userInfoCheckRecordRepository.findByUserUsername(username);
        for(UserInfoCheckRecord record : records) {
            CheckState state = record.getCheckState();
            List<String> evidences = userEvidenceRecordRepository.findEvidencesByItem(record);
            UserCheckItem item = new UserCheckItem(record, evidences);
            if(state.equals(CheckState.ONGING) || state.equals(CheckState.UPDATE)) {
                toApprove.add(item);
            }else {
                haveApproved.add(item);
            }
        }

        return new UserCheckResponse(true, "success", haveApproved, toApprove);
    }

    /**
     * 审批用户条目
     * @param username 用户名
     * @param id       条目编号
     * @param result   审批结果
     */
    @Override
    public ApiResponse approveItem(String username, Long id, ApproveResult result) {
        UserInfoCheckRecord checkRecord = userInfoCheckRecordRepository.findById(id).get();
        if(checkRecord.getCheckState().equals(CheckState.PASS) || checkRecord.getCheckState().equals(CheckState.REJECT)) {
            // 条目已经审批
            return new ApiResponse(false, "该条目已经审批");
        }

        CheckState state = result.getCheckState();
        String message = result.getStr();
        checkRecord.setCheckState(state);
        checkRecord.setMessage(message);
        userInfoCheckRecordRepository.save(checkRecord);

        List<BaseUserEvidence> baseUserEvidences = userEvidenceRecordRepository.findByItem(checkRecord);
        for(BaseUserEvidence record : baseUserEvidences) {
            record.setState(state);
            userEvidenceRecordRepository.save(record);
        }

        // 计算得分
        if(state.equals(CheckState.PASS)) {
            scoreCalUtil.calScore(checkRecord);
        }

        return ApiResponse.successResponse();
    }
    // 将UserInfoCheckRecord列表转为UserStateList列表
    private List<UserStateList> getList(List<UserInfoCheckRecord> records) {
        List<UserStateList> list = new ArrayList<>();

        if(records == null || records.size() == 0) {
            return list;
        }

        List<String> usernames = new ArrayList<>();
        for(UserInfoCheckRecord record : records) {
            String username = record.getUser().getUsername();
            if(usernames.contains(username)) {
                int index = usernames.indexOf(username);
                UserStateList pre = list.get(index);
                if(record.getCheckState().equals(CheckState.UPDATE) && pre.getCheckState().equals(CheckState.ONGING)) {
                    pre.setCheckState(CheckState.UPDATE);
                }
            }else {
                usernames.add(username);
                UserStateList userStateList = new UserStateList(record);
                list.add(userStateList);
            }
        }

        return list;
    }

/*    *//**
     * 得到待审核的标的列表
     * @param type 标的类别
     * @return 标的概要信息
     *//*
    private List<PendingTargetBriefInfo> getPendingTargets(TargetType type) {
        TargetState state = TargetState.PENDING;
        // 通过标的类型筛选得到标的信息
        List<BaseTarget> records;
        if(type == null) {
            records = baseTargetRepository.findDistinctByTargetState(state);
        }else {
            records = baseTargetRepository.findDistinctByTargetStateAndTargetType(state, type);
        }

        // 生产标的的概要信息
        List<PendingTargetBriefInfo> list = new ArrayList<>();
        if(records == null || records.size() == 0) {
            return list;
        }
        for(BaseTarget target : records) {
            PendingTargetBriefInfo briefInfo = new PendingTargetBriefInfo(target);
            list.add(briefInfo);
        }
        return list;
    }*/
/*
    *//**
     * 查看小额标的内容
     * @param id 标的编号
     * @return 小额标的的详细内容
     * 若id对应的不是小额标的，则返回null
     *//*
    @Override
    public SmallTargetInfo getSmallTargetInfo(Long id) {
        SmallTarget target = smallTargetRepository.findById(id).get();
        SmallTargetInfo info = new SmallTargetInfo(target);
        return info;
    }

    *//**
     * 查看大额标的内容
     * @param id 标的编号
     * @return 大额标的的详细内容
     * 若id对应的不是大额标的，则返回null
     *//*
    @Override
    public LargeTargetInfo getLargeTargetInfo(Long id) {
        LargeTarget target = largeTargetRepository.findById(id).get();
        LargeTargetInfo info = new LargeTargetInfo(target);
        return info;
    }*/

    private PendingTargetDetailInfo getPendingTarget(Long id) {
        BaseTarget baseTarget = baseTargetRepository.findById(id).get();
        User user = baseTarget.getUser();
        TargetType targetType = baseTarget.getTargetType();
        CheckState state = scoreCalUtil.checkUserState(user.getUsername());
        if(targetType.equals(TargetType.LARGE)) {
            LargeTarget largeTarget = largeTargetRepository.findById(id).get();
            LargeProjectClassification classification = largeTarget.getClassification();
            Repayment repayment = repaymentRepository.findFirstByTargetId(id);
            return new PendingTargetDetailInfo(user, baseTarget, repayment, state, classification);
        }else {
            SmallTarget smallTarget = smallTargetRepository.findById(id).get();
            SmallProjectClassification classification = smallTarget.getClassification();
            Repayment repayment = repaymentRepository.findFirstByTargetId(id);
            return new PendingTargetDetailInfo(user, baseTarget, repayment, state, classification);
        }
    }

    /**
     * 查看待审核标的详情
     * @param type 类别
     * @return 标的详情
     */
    @Override
    public List<PendingTargetDetailInfo> getPendingTarget(TargetType type) {
        TargetState state = TargetState.PENDING;
        // 通过标的类型筛选得到标的信息
        List<BaseTarget> records;
        List<PendingTargetDetailInfo> list = new ArrayList<>();
        if(type == null) {
            records = baseTargetRepository.findDistinctByTargetState(state);
        }else {
            records = baseTargetRepository.findDistinctByTargetStateAndTargetType(state, type);
        }
        for(BaseTarget target : records) {
            PendingTargetDetailInfo pendingTargetDetailInfo = getPendingTarget(target.getId());
            list.add(pendingTargetDetailInfo);
        }
        return list;
    }

    /**
     * 审批标的
     * @param targetId 标的编号
     * @param result 审批结果
     * @return 是否成功
     */
    @Override
    public ApiResponse approveTarget(Long targetId, ApproveResult result) {
        if(!baseTargetRepository.existsById(targetId)) {
            return new ApiResponse(false, "该任务不存在");
        }
        if(result == null) {
            return new ApiResponse(false, "请选择“通过”或“拒绝”");
        }
        BaseTarget target = baseTargetRepository.findById(targetId).get();
        if(!target.getTargetState().equals(TargetState.PENDING)) {
            return new ApiResponse(false, "该任务已经审核");
        }

        // 审核操作成功，数据库进行存储
        target.setTargetState(result.getTargetState());
        baseTargetRepository.save(target);
        return ApiResponse.successResponse();
    }
}
