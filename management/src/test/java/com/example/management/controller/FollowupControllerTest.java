package com.example.management.controller;

import com.example.management.utils.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class FollowupControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(authorities = {"联系跟进"})
    void addFollowup() {  //添加跟进
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request = post("http://localhost:9090/followup/add")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
                .param("staffQQ","2316548")
                .param("customerQQ","231654")
                .param("contactWay","电话联系")
                .param("result","很好！");
        try{
            System.out.println(request);
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
            Assertions.assertEquals(result.getCode(), 200);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback()
    @WithMockUser(authorities = {"联系跟进"})
    void addFollowupError() {  //用户或客户不存在
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request = post("http://localhost:9090/followup/add")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
                .param("staffQQ","345644521")
                .param("customerQQ","165412")
                .param("contactWay","电话联系")
                .param("result","很好！");
        try{
            System.out.println(request);
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
            Assertions.assertEquals(result.getCode(), 400);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(authorities = {"联系跟进"})
    void allFollowup() {  //查询所有的联系跟进记录
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request = post("http://localhost:9090/followup/all")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A");
        try{
            System.out.println(request);
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
            System.out.println(result.getData());
            Assertions.assertEquals(result.getCode(), 200);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(authorities = {"联系跟进"})
    void selectCustomerFollowup() {  //查询对某个客户写的联系跟进
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request = post("http://localhost:9090/followup/customer")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
                .param("customerId","2");
        try{
            System.out.println(request);
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
            System.out.println(result.getData());
            Assertions.assertEquals(result.getCode(), 200);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(authorities = {"联系跟进"})
    void selectStaffFollowup() {  //查询某个员工写的所有跟进
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request = post("http://localhost:9090/followup/staff")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YjE2MTI1OGIxNzA0N2MzYjFlYjgzY2FmYWY1ZGMxZiIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODA0MzY5NTYsImV4cCI6MTY4MDQ0Nzc1Nn0._0pSJyRGhOSbeeTWLHjH_o99KTTUSQ1YI_BfB3VDh7A")
                .param("staffId","27");
        try{
            System.out.println(request);
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
            System.out.println(result.getData());
            Assertions.assertEquals(result.getCode(), 200);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}