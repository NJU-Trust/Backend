/*
package nju.trust.web.admin;

import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.awt.print.Pageable;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

*/
/**
 * @Author: 许杨
 * @Description: AdminController的测试
 * @Date: 2018/9/13
 *//*

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AdminUserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getUserListLen() throws Exception {
        MvcResult result = mvc.perform(
                get("/adminUser/manageLen")
                        .param("keyword", "")
                        .param("type", "")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getUserList() throws Exception {

        MvcResult result = mvc.perform(
                get("/adminUser/manage")
                        .param("size","2")
                        .param("page","0")
                        .param("keyword", "null")
                        .param("type", "null")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getUserStateList() throws Exception {
        mvc.perform(
                get("/adminUser/userlist")
                        //分页查询参数，第三页，每页15个数据，按照年龄倒序排序
                        .param("size","2")
                        .param("page","1")
                        //.param("sort","age,desc")//发送get请求，并带请求参数

                        .contentType(MediaType.APPLICATION_JSON_UTF8) //编码格式为json的utf8
        ).andExpect(status().isOk())  //返回的状态码为200 OK
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getUserCheckItems() throws Exception {
        MvcResult result = mvc.perform(
                get("/adminUser/userpendingitem")
                        .param("username", "test")
        ).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void approveItem() throws Exception {
        MvcResult result = mvc.perform(
                get("/adminUser/usercheck")
                        .param("username", "test")
                        .param("id", "1")
                        .param("result", "PASS")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}*/
