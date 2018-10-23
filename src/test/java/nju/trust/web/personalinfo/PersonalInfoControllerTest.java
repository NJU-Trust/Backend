//package nju.trust.web.personalinfo;
//
//import nju.trust.payloads.personalinfomation.PersonalRelationship;
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
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * @Author: 许杨
// * @Description:
// * @Date: 2018/9/15
// */
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//public class PersonalInfoControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    public void getCampusPerformance() throws Exception{
//        MvcResult result = mvc.perform(
//                get("/profile/campusPerformence")
//                        .param("username", "test")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    public void getPersonalDetailInformation() throws Exception{
//        MvcResult result = mvc.perform(
//                get("/profile/information")
//                        .param("username", "test")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    public void getInvestAndLoanInfo() throws Exception{
//        MvcResult result = mvc.perform(
//                get("/profile/investAndLoan")
//                        .param("username", "test")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    public void getPersonalRelationships() throws Exception{
//        MvcResult result = mvc.perform(
//                get("/profile/relationship")
//                        .param("username", "test")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    public void getTotalAccountInfo() throws Exception{
//        MvcResult result = mvc.perform(
//                get("/profile/totalAccount")
//                        .param("username", "test")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    public void getAllEventsInfo() throws Exception{
//        MvcResult result = mvc.perform(
//                get("/profile/todo")
//                        .param("username", "test")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    public void getDataAnalysis() throws Exception{
//        MvcResult result = mvc.perform(
//                get("/profile/dataAnalysis")
//                        .param("username", "test")
//                        .param("startMonth", "2018-9")
//                        .param("endMonth", "2018-10")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    public void getTrendAnalysis() throws Exception{
//        MvcResult result = mvc.perform(
//                get("/profile/trendAnalysis")
//                        .param("username", "test")
//                        .param("startMonth", "2018-9")
//                        .param("endMonth", "2018-10")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    public void getProportionAnalysis() throws Exception{
//        MvcResult result = mvc.perform(
//                get("/profile/proportionAnalysis")
//                        .param("username", "test")
//                        .param("month", "2018-9")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//}
