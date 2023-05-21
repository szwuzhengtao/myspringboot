//package com.example.management.controller;
//
//import com.example.management.mapper.CustomerMapper;
//import com.example.management.utils.CommonResult;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
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
//class DeletedControllerTest {
//    @Autowired
//    private DeletedController deletedController;
//
//    @Autowired
//    private CustomerMapper customerMapper;
//
//    @Autowired(required = false)
//    private MockMvc mvc;
//    @Autowired
//    private WebApplicationContext wac;
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    @Transactional
//    @Rollback()
//    @WithMockUser(authorities = {"恢复删除"})
//    void restore() {
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        RequestBuilder request = post("http://localhost:9090/deleted/restore")
//                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
//                .param("customerId","32");
//        try{
//            System.out.println(request);
//            Integer num1 = customerMapper.selectCount(null);
//            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
//            ObjectMapper mapper = new ObjectMapper();
//            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
//            Assertions.assertEquals(result.getCode(), 200);
//            Integer num2 = customerMapper.selectCount(null);
//            Assertions.assertEquals(num2-num1,1);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @Transactional
//    @Rollback()
//    @WithMockUser(authorities = {"删除客户"})
//    void allDelete() {
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        RequestBuilder request = post("http://localhost:9090/deleted/all")
//                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
//                .param("staffId","1");
//        try{
//            System.out.println(request);
//            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
//            ObjectMapper mapper = new ObjectMapper();
//            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
//            System.out.println(result);
//            Assertions.assertEquals(result.getCode(), 200);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}