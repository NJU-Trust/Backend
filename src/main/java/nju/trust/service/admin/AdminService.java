package nju.trust.service.admin;

import nju.trust.entity.*;
import nju.trust.entity.record.ApproveResult;
import nju.trust.entity.record.RepaymentRecord;
import nju.trust.entity.target.TargetState;
import nju.trust.entity.target.TargetType;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.admin.*;
import nju.trust.payloads.target.LargeTargetInfo;
import nju.trust.payloads.target.SmallTargetInfo;
import nju.trust.payloads.target.TargetInfo;
import nju.trust.payloads.user.UserSimpleInfo;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: 161250127
 * @Description: 用于【管理员】用户管理
 * @Date: 2018/8/26
 */
public interface AdminService {
    // 用户管理
    int getUserListLen(String keyword, UserType type);
    /**
     * 查找所有用户的概要信息
     * @param keyword 关键字
     * @param type 用户类别（借款用户：无借款用户、待还款用户、逾期用户；投资用户）
     * @return List<UserSimpleInfo>
     */
    List<UserSimpleInfo> getUserList(String keyword, UserType type);

    /**
     * 财务信息
     * @param username 用户昵称
     * @return 财务信息
     */
    // 修改返回值
    //AssetStatistics searchFinancialInfo(String username);

    /**
     * 标的查看
     * @param username 用户昵称
     * @return 发起过的标的
     */
    //ArrayList<TargetInfo> seeLaunchTargets(String username);

    /**
     * 投资历史
     * @param username 用户昵称
     * @return 投资过的标的
     */
    //ArrayList<TargetInfo> seeInvestTargets(String username);


    // 标的管理
    /**
     * 查看项目
     * @param state 项目状态
     * @param type 项目类型（小额拆借类、学习培训类）
     * @return
     */
    List<TargetAdminBriefInfo> seeTarget(TargetState state, TargetType type);

    /**
     * 查看项目信息
     * @param id 项目编号
     * @return 项目的详细信息
     */
    TargetAdminDetailInfo seeTarget(Long id);

    // 数据统计（针对平台）
    /**
     * 统计基础数据
     * @return 当天
     * 总额：交易总额、交易总笔数、借款人数量、投资人数量
     * 人均统计：人均累计借款额度、笔均借款额度、人均累计投资额度
     * 其他统计：最大单户借款余额占比、最大10户借款余额占比、平均满标时间
     */
    BaseStatistics getBaseStatistics();

    /**
     * 统计违约信息
     * @return 当月
     * 累计违约率、逾期项目数、项目逾期率、近三月项目逾期率、借款逾期金额
     * 待偿金额、借贷金额逾期率、借贷坏账率、客户投诉情况
     */
    BreakContractStatistics getBreakContractStatistics();

    // 管理审核
    /**
     * 用户审核时得到待审核用户及其状态的列表
     * 优先级：UPDATE > SUBMIT 时间早 > 时间晚
     * @return List<UserStateList>
     */
    List<UserStateList> getUserStateList();

    /**
     * 返回用户的待审核条目
     * @param username 用户名
     * @return 待审核条目信息及审核通过的
     */
    UserCheckResponse getUserCheckItems(String username);

    /**
     * 审批用户条目
     * @param username 用户名
     * @param id 条目编号
     * @param result 审批结果
     * @return
     */
    ApiResponse approveItem(String username, Long id, ApproveResult result);

    // 标的发布审核
    /**
     * 得到待审核的标的列表
     * @param type 标的类别
     * @return 标的概要信息
     */
    //public List<PendingTargetBriefInfo> getPendingTargets(TargetType type);

    /**
     * 得到待审核的小额标的编号
     * @return 项目编号
     */
//    List<Long> getPendingSmallTargets();

    /**
     * 查看小额标的内容
     * @param id 标的编号
     * @return 小额标的的详细内容
     * 若id对应的不是小额标的，则返回null
     */
    //SmallTargetInfo getSmallTargetInfo(Long id);

    /**
     * 得到待审核的大额标的编号
     * @return 项目编号
     */
//    List<Long> getPendingLargeTargets();

    /**
     * 查看大额标的内容
     * @param id 标的编号
     * @return 大额标的的详细内容
     * 若id对应的不是大额标的，则返回null
     */
    //LargeTargetInfo getLargeTargetInfo(Long id);

    /**
     * 查看标的详情
     * @param id 标的编号
     * @return 标的详情
     */
    //PendingTargetDetailInfo getPendingTarget(Long id);

    /**
     * 审批标的
     * @param targetId 标的编号
     * @param result 审批结果
     * @return 是否成功
     */
    ApiResponse approveTarget(Long targetId, ApproveResult result);

    /**
     * 判断是非为坏账
     * @param repaymentRecord
     * @return
     */
    boolean isViolate(RepaymentRecord repaymentRecord);

    /**
     * 查看待审核标的详情
     * @param type 类别
     * @return 标的详情
     */
    List<PendingTargetDetailInfo> getPendingTarget(TargetType type);
}
