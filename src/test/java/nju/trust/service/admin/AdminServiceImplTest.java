package nju.trust.service.admin;

import nju.trust.entity.UserType;
import nju.trust.entity.record.ApproveResult;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.admin.UserCheckItem;
import nju.trust.payloads.admin.UserCheckResponse;
import nju.trust.payloads.admin.UserStateList;
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
    }

    @Test
    public void getBreakContractStatistics() {
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