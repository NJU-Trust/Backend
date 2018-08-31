package nju.trust.service;

import nju.trust.entity.*;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import nju.trust.payloads.admin.BaseStatistics;
import nju.trust.payloads.admin.BreakContractStatistics;
import nju.trust.payloads.admin.UserStateList;
import nju.trust.payloads.target.LargeTargetInfo;
import nju.trust.payloads.target.SmallTargetInfo;
import nju.trust.payloads.target.TargetAdminBriefInfo;
import nju.trust.payloads.target.TargetInfo;
import nju.trust.payloads.user.UserSimpleInfo;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.SignUpRequest;
import nju.trust.payloads.user.UserInformation;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 161250127
 * @Description: 用于【管理员】用户管理
 * @Date: 2018/8/26
 */
@Service
public interface AdminService {
    // 用户管理
    /**
     * 查找所有用户的概要信息
     * @param keyword 关键字
     * @param type 用户类别（借款用户：无借款用户、待还款用户、逾期用户；投资用户）
     * @return List<UserSimpleInfo>
     */
    List<UserSimpleInfo> getUserList(Pageable pageable, String keyword, UserType type);

    /**
     * 管理员添加用户(默认用户为初级用户)
     * @param userInfo 用户信息
     * 用户名邮箱手机等非空
     * @return
     */
    /*    ApiResponse addUser(SignUpRequest userInfo);*/

    /**
     * 管理员修改用户信息
     * @param userInfo 初级用户信息
     *                 默认用户为初级用户
     *                 用户名邮箱手机等非空
     * @return
     */
    /*    ApiResponse modifyUser(UserInformation userInfo);*/

    /**
     * 管理员删除用户
     * @param username 用户昵称
     * @return
     */
    /*  ApiResponse deleteUser(UserInformation username);*/

    /**
     * 管理员查找用户信息
     * @param keyword 关键字
     *                模糊查找(通过编号、昵称等搜索)
     * @param userType 用户类型
     *                 (无借款用户、待还款用户、逾期用户)
     * @return 用户编号 昵称 财务信息  信用评级标的查看
     */
    ArrayList<UserSimpleInfo> searchBorrowers(String keyword, UserType userType);

    /**
     * TODO 财务信息
     * @param username 用户昵称
     * @return 财务信息
     */
    // TODO 修改返回值
    //AssetStatistics searchFinancialInfo(String username);

    /**
     * 标的查看
     * @param username 用户昵称
     * @return 发起过的标的
     */
    ArrayList<TargetInfo> seeLaunchTargets(String username);

    /**
     * 投资历史
     * @param username 用户昵称
     * @return 投资过的标的
     */
    ArrayList<TargetInfo> seeInvestTargets(String username);


    // 标的管理
    /**
     * 查看项目
     * @param state 项目状态
     * @param type 项目类型（小额拆借类、学习培训类）
     * @return
     */
    ArrayList<TargetAdminBriefInfo> seeTarget(Pageable pageable, TargetState state, TargetType type);

    /**
     * 查看项目信息
     * @param id 项目编号
     * @return 项目的详细信息
     */
    TargetInfo seeTarget(Long id);

    // 数据统计（针对平台）
    /**
     * 统计基础数据
     * @return 当天
     * 总额：交易总额、交易总笔数、借款人数量、投资人数量
     * 人均统计：人均累计借款额度、笔均借款额度、人均累计投资额度
     * 其他统计：最大单户借款余额占比、最大10户借款余额占比、平均满标时间
     */
    List<BaseStatistics> getBaseStatistics();

    /**
     * 统计违约信息
     * @return 当月
     * 累计违约率、逾期项目数、项目逾期率、近三月项目逾期率、借款逾期金额
     * 待偿金额、借贷金额逾期率、借贷坏账率、客户投诉情况
     */
    List<BreakContractStatistics> getBreakContractStatistics();

    // 管理审核
    // TODO 用户审核

    /**
     * 用户审核时得到待审核用户及其状态的列表
     * 优先级：UPDATE > SUBMIT 时间早 > 时间晚
     * @return List<UserStateList>
     */
    List<UserStateList> getUserStateList();



    // 标的发布审核
    /**
     * 得到待审核的小额标的编号
     * @return 项目编号
     */
    List<Long> getPendingSmallTargets();

    /**
     * 查看小额标的内容
     * @param id 标的编号
     * @return 小额标的的详细内容
     * 若id对应的不是小额标的，则返回null
     */
    SmallTargetInfo getSmallTargetInfo(Long id);

    /**
     * 得到待审核的大额标的编号
     * @return 项目编号
     */
    List<Long> getPendingLargeTargets();

    /**
     * 查看大额标的内容
     * @param id 标的编号
     * @return 大额标的的详细内容
     * 若id对应的不是大额标的，则返回null
     */
    LargeTargetInfo getLargeTargetInfo(Long id);

    /**
     * 审批标的
     * @param targetId 标的编号
     * @return PASS|REJECT
     */
    CheckState approveTarget(Long targetId);
}
