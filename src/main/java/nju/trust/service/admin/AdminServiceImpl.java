package nju.trust.service.admin;

import nju.trust.dao.admin.AdminUserRepository;
import nju.trust.dao.admin.BaseTargetReposity;
import nju.trust.dao.admin.InvestmentRecordRepository;
import nju.trust.entity.CheckState;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import nju.trust.entity.UserType;
import nju.trust.entity.user.User;
import nju.trust.payloads.admin.BaseStatistics;
import nju.trust.payloads.admin.BreakContractStatistics;
import nju.trust.payloads.admin.UserStateList;
import nju.trust.payloads.target.LargeTargetInfo;
import nju.trust.payloads.target.SmallTargetInfo;
import nju.trust.payloads.admin.TargetAdminBriefInfo;
import nju.trust.payloads.target.TargetInfo;
import nju.trust.payloads.user.UserSimpleInfo;
import nju.trust.service.AdminService;
import nju.trust.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 161250127
 * @Description: 管理员模块的逻辑层实现
 * @Date: 2018/8/26
 */
public class AdminServiceImpl implements AdminService {
    @Autowired
    private TargetService targetService;
    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private BaseTargetReposity baseTargetReposity;
    @Autowired
    private InvestmentRecordRepository investmentRecordRepository;

    /**
     * 查找所有用户的概要信息
     * @param keyword 关键字
     * @param type 用户类别（借款用户：无借款用户、待还款用户、逾期用户；投资用户）
     * TODO test
     * @return List<UserSimpleInfo>
     */
    @Override
    public List<UserSimpleInfo> getUserList(Pageable pageable, String keyword, UserType type) {
        List<String> usernames;
        // 通过模糊查询查找用户姓名
        if(keyword == null || keyword.equals("")) { // 关键字不存在，返回所有用户姓名
            usernames = adminUserRepository.getAllName();
        }else {
            usernames = adminUserRepository.getNamesByKeyword(keyword);
        }

        // 通过用户类别筛选用户
        List<UserSimpleInfo> infoList;
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

        if(infoList == null || infoList.size() == 0) {
            return new ArrayList<>();
        }

        Page<UserSimpleInfo> pages = null;
        infoList = pages.stream().map(UserSimpleInfo::new).collect(Collectors.toList());

        return infoList;
    }
    // TODO test 查找用户是否欠款，返回未欠款/欠款用户的UserSimpleInfo
    private List<UserSimpleInfo> getLoanUserInfo(List<String> usernames, UserType type) {
        List<UserSimpleInfo> list = new ArrayList<>();
        for (String username : usernames) {
            LoanStateCheckUtil calUtil = new LoanStateCheckUtil(username);
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
    // TODO test 查找逾期用户（逾期金额>0）
    private List<UserSimpleInfo> getOverdueUserInfo(List<String> usernames) {
        int i = 0;
        while (i < usernames.size()) {
            String username = usernames.get(i);
            LoanStateCheckUtil calUtil = new LoanStateCheckUtil(username);
            if(!calUtil.checkUserType().equals(UserType.OVERDUE)) {// 不是逾期用户
                usernames.remove(i);
            }else {
                i++;
            }
        }
        List<User> users = (List<User>)adminUserRepository.findAllById(usernames);
        return getSimpleInfo(users, UserType.OVERDUE);
    }
    // TODO test 不区分用户类别时查看用户概要信息
    private List<UserSimpleInfo> getLoanUserInfo(List<String> usernames) {
        List<UserSimpleInfo> list = new ArrayList<>();
        for (String username : usernames) {
            LoanStateCheckUtil calUtil = new LoanStateCheckUtil(username);
            UserType type = calUtil.checkUserType();
            User user = adminUserRepository.findById(username).get();
            UserSimpleInfo userSimpleInfo = new UserSimpleInfo(user, type);
            list.add(userSimpleInfo);
        }
        return list;
    }
    /*    // 查找投资者
    private List<UserSimpleInfo> getInvestorInfo(List<String> usernames) {

        return null;
    }*/
    private List<UserSimpleInfo> getSimpleInfo(List<User> users, UserType type) {
        List<UserSimpleInfo> infos = new ArrayList<>();
        for(User user : users) {
            UserSimpleInfo info = new UserSimpleInfo(user, type);
            infos.add(info);
        }
        return infos;
    }

    /**
     * 查看项目
     * TODO test
     * @param state 项目状态(招标中,审核中,还款中,已还款)
     * @param type  项目类型（小额拆借类、学习培训类）
     * @return 项目的概要信息列表
     */
    @Override
    public List<TargetAdminBriefInfo> seeTarget(Pageable pageable, TargetState state, TargetType type) {
        List<BaseTarget> records;

        // 通过状态查看标的
        if(state != null && type != null) {
            records = baseTargetReposity.findDistinctByTargetStateAndTargetType(state, type);
        }else if(state == null){
            records = (List<BaseTarget>)baseTargetReposity.findAll();
            records = getTargetInfoByType(records, type, pageable);
        }else {
            records = baseTargetReposity.findDistinctByTargetState(state);
            records = getTargetInfoByType(records, type, pageable);
        }
        if(records == null || records.size() == 0) {
            return new ArrayList<>();
        }

        // BaseTarget转TargetAdminBriefInfo
        List<TargetAdminBriefInfo> infos = baseTargetsToBriefInfos(records);

        // 分页
        Page<TargetAdminBriefInfo> pages = null;
        infos = pages.stream().map(TargetAdminBriefInfo::new).collect(Collectors.toList());
        return infos;
    }
    // TODO test 通过type对target进行筛选并分页
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
        List<String> investors = investmentRecordRepository.findUserUsernameById(targetId);
        List nameList = new ArrayList(new HashSet(investors));// 去重
        TargetAdminBriefInfo briefInfo = new TargetAdminBriefInfo(baseTarget, nameList);
        return briefInfo;
    }

    /**
     * 查看项目信息
     * @param id 项目编号
     * @return 项目的详细信息
     */
    @Override
    public TargetInfo seeTarget(Long id) {
        return targetService.getTargetInfo(id);
    }

    /**
     * 统计基础数据
     * TODO
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
     * TODO
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
     * TODO
     * @return List<UserStateList>
     */
    @Override
    public List<UserStateList> getUserStateList() {
        return null;
    }

    /**
     * 得到待审核的小额标的编号
     * TODO
     * @return 项目编号
     */
    @Override
    public List<Long> getPendingSmallTargets() {
        return null;
    }

    /**
     * 查看小额标的内容
     * TODO
     * @param id 标的编号
     * @return 小额标的的详细内容
     * 若id对应的不是小额标的，则返回null
     */
    @Override
    public SmallTargetInfo getSmallTargetInfo(Long id) {
        return null;
    }

    /**
     * 得到待审核的大额标的编号
     * TODO
     * @return 项目编号
     */
    @Override
    public List<Long> getPendingLargeTargets() {
        return null;
    }

    /**
     * 查看大额标的内容
     * TODO
     * @param id 标的编号
     * @return 大额标的的详细内容
     * 若id对应的不是大额标的，则返回null
     */
    @Override
    public LargeTargetInfo getLargeTargetInfo(Long id) {
        return null;
    }

    /**
     * 审批标的
     * TODO
     * @param targetId 标的编号
     * @return PASS|REJECT
     */
    @Override
    public CheckState approveTarget(Long targetId) {
        return null;
    }
}
