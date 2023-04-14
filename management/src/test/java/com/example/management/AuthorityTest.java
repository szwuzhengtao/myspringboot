package com.example.management;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.management.domain.LoginStaff;
import com.example.management.utils.CommonResult;
import com.example.management.utils.JwtUtil;
import com.example.management.utils.RedisCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class AuthorityTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private RedisCache redisCache;

    @BeforeEach
    void setUp() {
    }

    @Test
    void showAuthority(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request = post("http://localhost:9090/user/login")
                .param("staffAccount","abc")
                .param("staffPassword","123456");
        try{
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
            Assertions.assertEquals(result.getCode(), 200);
            JSONObject jsonObject= JSON.parseObject(response);
            String token = jsonObject.getJSONObject("data").getString("token");
            Claims claims = JwtUtil.parseJWT(token);
            String userid = claims.getSubject();
            String redisKey = "login" + userid;
            LoginStaff loginUser = redisCache.getCacheObject(redisKey);

            System.out.println(loginUser.getAuthorities());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
