package nju.trust.service.admin;

import nju.trust.dao.admin.*;
import nju.trust.dao.admin.UserEvidenceDao.UserEvidenceRepository;
import nju.trust.dao.target.LargeTargetRepository;
import nju.trust.dao.target.SmallTargetRepository;
import nju.trust.dao.target.TargetRepository;
import nju.trust.entity.CheckItem;
import nju.trust.entity.CheckState;
import nju.trust.entity.record.ApproveResult;
import nju.trust.entity.record.UserEvidence.BaseUserEvidence;
import nju.trust.entity.record.UserInfoCheckRecord;
import nju.trust.entity.target.*;
import nju.trust.entity.UserType;
import nju.trust.entity.user.Repayment;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.admin.*;
import nju.trust.payloads.target.LargeTargetInfo;
import nju.trust.payloads.target.SmallTargetInfo;
import nju.trust.payloads.target.TargetInfo;
import nju.trust.payloads.user.UserSimpleInfo;
import nju.trust.service.target.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private TargetRepository targetRepository;
    private UserInfoCheckRecordRepository userInfoCheckRecordRepository;
    private RepaymentRepository repaymentRepository;
    private UserEvidenceRepository userEvidenceRecordRepository;
    private ScoreCalUtil scoreCalUtil;
    @Autowired
    public AdminServiceImpl(LoanStateCheckUtil calUtil,
                            AdminUserRepository adminUserRepository,
                            BaseTargetRepository baseTargetRepository,
                            AdminInvestmentRecordRepository adminInvestmentRecordRepository,
                            SmallTargetRepository smallTargetRepository,
                            LargeTargetRepository largeTargetRepository,
                            TargetRepository targetRepository,
                            UserInfoCheckRecordRepository userInfoCheckRecordRepository,
                            RepaymentRepository repaymentRepository,
                            UserEvidenceRepository userEvidenceRecordRepository,
                            ScoreCalUtil scoreCalUtil) {
        this.calUtil = calUtil;
        this.adminUserRepository = adminUserRepository;
        this.baseTargetRepository = baseTargetRepository;
        this.adminInvestmentRecordRepository = adminInvestmentRecordRepository;
        this.smallTargetRepository = smallTargetRepository;
        this.largeTargetRepository = largeTargetRepository;
        this.targetRepository = targetRepository;
        this.userInfoCheckRecordRepository = userInfoCheckRecordRepository;
        this.repaymentRepository = repaymentRepository;
        this.userEvidenceRecordRepository = userEvidenceRecordRepository;
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
    public List<UserSimpleInfo> getUserList(Pageable pageable, String keyword, UserType type) {
        // 通过模糊查询、用户类别筛选用户
        List<UserSimpleInfo> infoList = getUserSimpleList(keyword, type);

        // 分页
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<UserSimpleInfo> infoList2 = new ArrayList<>();
        for(int i = pageNumber*pageSize ; i < infoList.size() && i < (pageNumber+1)*pageSize ; i++) {
            infoList2.add(infoList.get(i));
        }

        return infoList2;
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
    public List<TargetAdminBriefInfo> seeTarget(Pageable pageable, TargetState state, TargetType type) {
        List<BaseTarget> records;

        // 通过状态查看标的
        if(state != null && type != null) {
            records = baseTargetRepository.findDistinctByTargetStateAndTargetType(state, type);
        }else if(state == null){
            records = (List<BaseTarget>) baseTargetRepository.findAll();
            records = getTargetInfoByType(records, type, pageable);
        }else {
            records = baseTargetRepository.findDistinctByTargetState(state);
            records = getTargetInfoByType(records, type, pageable);
        }
        if(records == null || records.size() == 0) {
            return new ArrayList<>();
        }

        // BaseTarget转TargetAdminBriefInfo
        List<TargetAdminBriefInfo> infos = baseTargetsToBriefInfos(records);

        // 分页
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<TargetAdminBriefInfo> infoList = new ArrayList<>();
        for(int i = pageNumber*pageSize ; i < infos.size() && i < (pageNumber+1)*pageSize ; i++) {
            infoList.add(infos.get(i));
        }

        return infoList;
    }
    // 通过type对target进行筛选并分页
    private List<BaseTarget> getTargetInfoByType(List<BaseTarget> records, TargetType type, Pageable pageable) {
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
        return new TargetAdminBriefInfo(baseTarget, nameList);
    }

    /**
     * 查看项目信息
     * @param id 项目编号
     * @return 项目的详细信息
     */
    @Override
    public TargetAdminDetailInfo seeTarget(Long id) {
        if(repaymentRepository.existsByTargetId(id) && targetRepository.existsById(id)) {
            System.out.println("repayment exists");
            BaseTarget baseTarget = targetRepository.findById(id).get();
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
    public List<BaseStatistics> getBaseStatistics() {
        return null;
    }

    /**
     * 统计违约信息
     * @return 当月
     * 累计违约率、逾期项目数、项目逾期率、近三月项目逾期率、借款逾期金额
     * 待偿金额、借贷金额逾期率、借贷坏账率、客户投诉情况
     */
    @Override
    public List<BreakContractStatistics> getBreakContractStatistics() {
        return null;
    }

    /**
     * 用户审核时得到待审核用户及其状态的列表
     * 优先级：UPDATE > SUBMIT 时间早 > 时间晚
     * @return List<UserStateList>
     */
    @Override
    public List<UserStateList> getUserStateList(Pageable pageable) {
        List<UserInfoCheckRecord> records = userInfoCheckRecordRepository.findByCheckState(CheckState.UPDATE);
        records.addAll(userInfoCheckRecordRepository.findByCheckState(CheckState.ONGING));

        List<UserStateList> list = getList(records);

        Collections.sort(list);

        // 分页
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<UserStateList> infoList = new ArrayList<>();
        for(int i = pageNumber*pageSize ; i < list.size() && i < (pageNumber+1)*pageSize ; i++) {
            infoList.add(list.get(i));
        }
        return infoList;
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

    /**
     * 得到待审核的标的列表
     * @param type 标的类别
     * @return 标的概要信息
     */
    @Override
    public List<PendingTargetBriefInfo> getPendingTargets(Pageable pageable, TargetType type) {
        TargetState state = TargetState.PENDING;
        // 通过标的类型筛选得到标的信息
        List<BaseTarget> records;
        if(type == null) {
            records = targetRepository.findByTargetState(state, pageable);
        }else {
            records = targetRepository.findByTargetTypeAndTargetState(type, state, pageable);
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
    }
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

    @Override
    public PendingTargetDetailInfo getPendingTarget(Long id) {
        BaseTarget baseTarget = targetRepository.findById(id).get();
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
     * 审批标的
     * @param targetId 标的编号
     * @param result 审批结果
     * @return 是否成功
     */
    @Override
    public ApiResponse approveTarget(Long targetId, ApproveResult result) {
        if(!targetRepository.existsById(targetId)) {
            return new ApiResponse(false, "该任务不存在");
        }
        if(result == null) {
            return new ApiResponse(false, "请选择“通过”或“拒绝”");
        }
        BaseTarget target = targetRepository.findById(targetId).get();
        if(!target.getTargetState().equals(TargetState.PENDING)) {
            return new ApiResponse(false, "该任务已经审核");
        }

        // 审核操作成功，数据库进行存储
        target.setTargetState(result.getTargetState());
        targetRepository.save(target);
        return ApiResponse.successResponse();
    }
}
