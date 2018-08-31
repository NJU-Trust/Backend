package nju.trust.service.admin;

import nju.trust.dao.admin.AdminUserRepository;
import nju.trust.dao.admin.RepaymentReposity;
import nju.trust.entity.CheckState;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import nju.trust.entity.UserType;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.SignUpRequest;
import nju.trust.payloads.admin.BaseStatistics;
import nju.trust.payloads.admin.BreakContractStatistics;
import nju.trust.payloads.admin.UserStateList;
import nju.trust.payloads.target.LargeTargetInfo;
import nju.trust.payloads.target.SmallTargetInfo;
import nju.trust.payloads.target.TargetAdminBriefInfo;
import nju.trust.payloads.target.TargetInfo;
import nju.trust.payloads.user.UserInformation;
import nju.trust.payloads.user.UserSimpleInfo;
import nju.trust.service.AdminService;
import nju.trust.service.TargetService;
import nju.trust.service.admin.OverdueCalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.ArrayList;
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
    private RepaymentReposity repaymentReposity;

    /**
     * 查找所有用户的概要信息
     * @param keyword 关键字
     * @param type 用户类别（借款用户：无借款用户、待还款用户、逾期用户；投资用户）
     * TODO
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

        // TODO 通过用户类别筛选用户
        List<UserSimpleInfo> infoList = new ArrayList<>();
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
            case INVESTMENT:// 投资用户
                infoList = getInvestorInfo(usernames);
                break;
            default:        // 不区分usertype
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
            Double remainingAmount = repaymentReposity.getRemainingAmount(username);
            if ((remainingAmount == 0 && type == UserType.NOLOAN) || // 为未欠款用户
                    remainingAmount > 0 && type == UserType.HAVELOAN) { // 欠款用户
                User user = adminUserRepository.findById(username).get();
                UserSimpleInfo userSimpleInfo = new UserSimpleInfo(user);
                list.add(userSimpleInfo);
            }
        }
        return list;
    }
    // TODO test 查找逾期用户（逾期金额>0）
    private List<UserSimpleInfo> getOverdueUserInfo(List<String> usernames) {
        for (String username : usernames) {
            OverdueCalUtil calUtil = new OverdueCalUtil(username);
            if(!calUtil.isOverdue()) {
                usernames.remove(username);
            }
        }
        List<User> users = (List<User>)adminUserRepository.findAllById(usernames);
        return getSimpleInfo(users);
    }
    // TODO 查找投资者
    private List<UserSimpleInfo> getInvestorInfo(List<String> usernames) {

        return null;
    }
    private List<UserSimpleInfo> getSimpleInfo(List<User> users) {
        List<UserSimpleInfo> infos = new ArrayList<>();
        for(User user : users) {
            UserSimpleInfo info = new UserSimpleInfo(user);
            infos.add(info);
        }
        return infos;
    }
    /**
     * 管理员查找用户信息
     * TODO
     * @param keyword  关键字
     *                 模糊查找(通过编号、昵称等搜索)
     * @param userType 用户类型
     *                 (无借款用户、待还款用户、逾期用户)
     * @return 用户编号 昵称 财务信息  信用评级标的查看
     */
    @Override
    public ArrayList<UserSimpleInfo> searchBorrowers(String keyword, UserType userType) {
        return null;
    }

    /**
     * 财务信息
     * TODO
     * @param username 用户昵称
     * @return 财务信息
     */
/*    @Override
    public AssetStatistics searchFinancialInfo(String username) {
        return null;
    }*/

    /**
     * 标的查看
     * TODO
     * @param username 用户昵称
     * @return 发起过的标的
     */
    @Override
    public ArrayList<TargetInfo> seeLaunchTargets(String username) {
        return null;
    }

    /**
     * 投资历史
     * TODO
     * @param username 用户昵称
     * @return 投资过的标的
     */
    @Override
    public ArrayList<TargetInfo> seeInvestTargets(String username) {
        return null;
    }

    /**
     * 查看项目
     * TODO
     * @param state 项目状态(招标中,审核中,还款中,已还款)
     * @param type  项目类型（小额拆借类、学习培训类）
     * @return
     */
    @Override
    public ArrayList<TargetAdminBriefInfo> seeTarget(Pageable pageable, TargetState state, TargetType type) {
        ArrayList<TargetAdminBriefInfo> infos = new ArrayList<>();
        switch (type) {
            case LARGE: infos = getLargeTargetInfo(pageable, state);
            case SMALL: infos = getSmallTargetInfo(pageable, state);
            default: infos = getTargetInfo(pageable, state);
        }
        return infos;
    }
    // TODO 大额
    private ArrayList<TargetAdminBriefInfo> getLargeTargetInfo(Pageable pageable, TargetState state) {
        ArrayList<TargetAdminBriefInfo> infos= new ArrayList<>();
        if(infos == null) {
            infos = new ArrayList<>();
        }
        return infos;
    }
    // TODO 小额
    private ArrayList<TargetAdminBriefInfo> getSmallTargetInfo(Pageable pageable, TargetState state) {
        ArrayList<TargetAdminBriefInfo> infos = new ArrayList<>();
        if(infos == null) {
            infos = new ArrayList<>();
        }
        return infos;
    }
    // 不区分大额、小额
    private ArrayList<TargetAdminBriefInfo> getTargetInfo(Pageable pageable, TargetState state) {
        ArrayList<TargetAdminBriefInfo> infos = new ArrayList<>();
        infos.addAll(getSmallTargetInfo(pageable, state));
        infos.addAll(getLargeTargetInfo(pageable, state));
        return infos;
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
