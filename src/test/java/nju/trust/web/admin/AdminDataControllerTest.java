//
//package nju.trust.web.admin;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * @Author: 许杨
// * @Description: AdminDataController的测试
// * @Date: 2018/10/20
// */
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//public class AdminDataControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    public void getBaseStatistics() {
//        try {
//            MvcResult result = mvc.perform(
//                    get("/adminData/base")
//                            .contentType(MediaType.APPLICATION_JSON_VALUE))
//                    .andExpect(status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    .andReturn();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getBreakContractStatistics() {
//        try {
//            MvcResult result = mvc.perform(
//                    get("/adminData/breakContract")
//                            .contentType(MediaType.APPLICATION_JSON_VALUE))
//                    .andExpect(status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    .andReturn();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
