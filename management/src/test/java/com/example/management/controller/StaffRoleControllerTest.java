//package com.example.management.controller;
//
//import com.example.management.utils.CommonResult;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@SpringBootTest
//class StaffRoleControllerTest {
//    private MockMvc mvc;
//    @Autowired
//    private WebApplicationContext wac;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    @Transactional
//    @Rollback()
//    @WithMockUser(authorities = {"设置权限"})
//    void setStaffRole() {  //添加某个员工的角色
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        RequestBuilder request = post("http://localhost:9090/staff-role/set")
//                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
//                .param("staffId","15")
//                .param("roleId","1");
//        try{
//            System.out.println(request);
//            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
//            ObjectMapper mapper = new ObjectMapper();
//            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
//            Assertions.assertEquals(result.getCode(), 200);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @Transactional
//    @Rollback()
//    @WithMockUser(authorities = {"设置权限"})
//    void setStaffRoleError() {  //该员工已有此角色
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        RequestBuilder request = post("http://localhost:9090/staff-role/set")
//                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
//                .param("staffId","1")
//                .param("roleId","10");
//        try{
//            System.out.println(request);
//            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
//            ObjectMapper mapper = new ObjectMapper();
//            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
//            Assertions.assertEquals(result.getCode(), 400);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @WithMockUser(authorities = {"设置权限"})
//    void selectStaffRole() {  //查询该员工的所有角色
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        RequestBuilder request = post("http://localhost:9090/staff-role/select")
//                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A");
//        try{
//            System.out.println(request);
//            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
//            ObjectMapper mapper = new ObjectMapper();
//            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
//            System.out.println(result.getData());
//            Assertions.assertEquals(result.getCode(), 200);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @Transactional
//    @Rollback()
//    @WithMockUser(authorities = {"设置权限"})
//    void deleteStaffRole() {  //删除该员工的某个角色
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        RequestBuilder request = post("http://localhost:9090/staff-role/delete")
//                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
//                .param("staffId","1")
//                .param("roleId","10");
//        try{
//            System.out.println(request);
//            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
//            ObjectMapper mapper = new ObjectMapper();
//            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
//            Assertions.assertEquals(result.getCode(), 200);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}