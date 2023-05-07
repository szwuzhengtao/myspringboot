package com.example.management.controller;

import com.example.management.utils.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class BlockControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void setUp() {
    }

    @Test
    void selectCustomerBlock() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request = post("http://localhost:9090/block/select/person")
                .param("personId","741260264")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YWZjMTZhY2YxMjA0MTI3OTZiYzExYzM1NzkxMzY2ZCIsInN1YiI6ImFiY2RlZWVlZSIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2NzkyMzQxNTMsImV4cCI6MTY3OTIzNzc1M30.lSU_4m1CvOfVoifwaUds2SbMPHAwLf4mEdeFJReYqeQ");
        try{
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
            System.out.println(result);
            Assertions.assertEquals(result.getCode(), 200);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void blockDetails() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request = post("http://localhost:9090/block/details")
                .param("blockId","5")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YWZjMTZhY2YxMjA0MTI3OTZiYzExYzM1NzkxMzY2ZCIsInN1YiI6ImFiY2RlZWVlZSIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2NzkyMzQxNTMsImV4cCI6MTY3OTIzNzc1M30.lSU_4m1CvOfVoifwaUds2SbMPHAwLf4mEdeFJReYqeQ");
        try{
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
            System.out.println(result);
            Assertions.assertEquals(result.getCode(), 200);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}