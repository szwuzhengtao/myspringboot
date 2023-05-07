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
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@SpringBootTest
//class ManuControllerTest {
//    private MockMvc mvc;
//    @Autowired
//    private WebApplicationContext wac;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    @WithMockUser(authorities = {"设置权限"})
//    void allManu() {  //测试查询所有权限
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        RequestBuilder request = post("http://localhost:9090/manu/all")
//                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
//                ;
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
//}