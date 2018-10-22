package nju.trust.service.admin;

import nju.trust.entity.UserType;
import nju.trust.entity.record.ApproveResult;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.admin.*;
import nju.trust.payloads.user.UserSimpleInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2018/9/13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AdminServiceImplTest {
    @Autowired
    private AdminService test;

    @Test
    public void getUserList() {
        System.out.println("test getUserList");
        Pageable pageable = new PageRequest(0, 1, null);
        String keyword = "test";
        UserType type = null;
        System.out.println("page:0  size:1  sort:null");
        System.out.println("keyword:"+keyword);
        System.out.println("type:"+type);
        List<UserSimpleInfo> result = test.getUserList(pageable, keyword, type);
        print(result);
    }
    private void print(List<UserSimpleInfo> userSimpleInfos) {
        for(UserSimpleInfo info : userSimpleInfos) {
            print(info);
        }
    }
    private void print(UserSimpleInfo info) {
        System.out.println("username:"+info.getUsername()+"  "+
                "level:"+info.getLevel()+"  "+
                "tel:"+info.getTel()+"  "+
                "email:"+info.getEmail()+"  "+
                "state:"+info.getState()+"  ");

    }

    @Test
    public void seeTarget() {
    }

    @Test
    public void seeTarget1() {
    }

    @Test
    public void getBaseStatistics() {
        BaseStatistics baseStatistics = test.getBaseStatistics();
        print(baseStatistics);
    }
    private void print(BaseStatistics baseStatistics) {
        System.out.println("BaseStatistics:");
        System.out.println("dealMoneySum:"+baseStatistics.getDealMoneySum());
        System.out.println("dealNum:"+baseStatistics.getDealNum());
        System.out.println("borrowerNum:"+baseStatistics.getBorrowerNum());
        System.out.println("investorNum:"+baseStatistics.getInvestorNum());
        System.out.println("loanPerPerson:"+baseStatistics.getLoanPerPerson());
        System.out.println("loanPerTarget:"+baseStatistics.getLoanPerTarget());
        System.out.println("investmentPerPerson:"+baseStatistics.getInvestmentPerPerson());
        System.out.println("mostLoanPersonRate:"+baseStatistics.getMostLoanPersonRate());
        System.out.println("most10LoanPersonRate:"+baseStatistics.getMost10LoanPersonRate());
        System.out.println("averageGoingTime:"+baseStatistics.getAverageGoingTime());
    }

    @Test
    public void getBreakContractStatistics() {
        BreakContractStatistics breakContractStatistics = test.getBreakContractStatistics();
        print(breakContractStatistics);
    }
    private void print(BreakContractStatistics breakContractStatistics) {
        System.out.println();
        System.out.println("breakContractStatistics:");
        System.out.println("累计违约率 defaultRate = "+breakContractStatistics.getDefaultRate());
        System.out.println("逾期项目数 overdueProgramNum = "+breakContractStatistics.getOverdueProgramNum());
        System.out.println("项目逾期率 overdueProgramRate = "+breakContractStatistics.getOverdueProgramRate());
        System.out.println("近三月项目逾期率 overdueProgramRate3 = "+breakContractStatistics.getOverdueProgramRate3());
        System.out.println("借款逾期金额 overdueMoneySum = "+breakContractStatistics.getOverdueMoneySum());
        System.out.println("待偿金额 toPay = "+breakContractStatistics.getToPay());
        System.out.println("借贷金额逾期率 overdueMoneyRate = "+breakContractStatistics.getOverdueMoneyRate());
        System.out.println("借贷坏账率 badDebtRate = "+breakContractStatistics.getBadDebtRate());
        System.out.println("客户投诉情况 complaints = "+breakContractStatistics.getComplaints());
    }

    @Test
    public void getUserStateList() {
        Pageable pageable = new PageRequest(1, 2, null);
        List<UserStateList> result = test.getUserStateList(pageable);
        printUserStateList(result);
    }
    private void printUserStateList(List<UserStateList> lists) {
        if(lists == null) {
            System.out.println("lists == null");
        }else if(lists.size() == 0) {
            System.out.println("lists.size() == 0");
        }else {
            for(UserStateList list : lists) {
                print(list);
            }
        }
    }
    private void print(UserStateList list) {
        System.out.println("username:"+list.getUsername()+"  "+
                "checkState:"+list.getCheckState()+"  "+
                "time:"+list.getTime());
    }

    @Test
    public void getUserCheckItems() {
        System.out.println("test: getUserCheckItems");
        String username = "test";
        System.out.println("username:"+username);

        UserCheckResponse response = test.getUserCheckItems(username);
        System.out.println("result:");
        print(response);
    }
    private void print(UserCheckResponse response) {
        print(response.getHaveApproved(), "haveApproved");
        print(response.getToApprove(), "toApprove");
    }
    private void print(List<UserCheckItem> items, String name) {
        if(items == null) {
            System.out.println(name + " == null");
        }else if(items.size() == 0) {
            System.out.println(name+".size() == 0");
        }else {
            System.out.println(name+":");
            for(UserCheckItem item : items) {
                System.out.print("id:"+item.getId() + "  " +
                        "item:"+item.getItem() + "  " +
                        "checkState:"+item.getCheckState() + "  " +
                        "description:"+item.getDescription() + "  " +
                        "evidences:");
                List<String> evidences = item.getEvidences();
                for(String str : evidences) {
                    System.out.print(str+"  ");
                }
                System.out.println();
            }
        }
    }

    @Test
    public void approveItem() {
        System.out.println("test approveItem");
        String username = "test";
        Long id = (long)1;
        ApproveResult result = ApproveResult.PASS;
        System.out.println("username:"+username+"  id:"+id+"  result:"+result);
        ApiResponse response = test.approveItem(username, id, result);
        System.out.println("response:"+response.getSuccess()+"  "+response.getMessage());
    }

    @Test
    public void getPendingTargets() {
    }

    @Test
    public void getPendingTarget() {
    }

    @Test
    public void approveTarget() {
    }

}