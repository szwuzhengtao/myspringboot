package com.example.management;

import com.example.management.mapper.CustomerMapper;
import com.example.management.pojo.po.Customer;
import com.example.management.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RedisTest {
    @Autowired(required = false)
    private CustomerMapper customerMapper;

    @Autowired
    private RedisCache redisCache;

    @Test
    public void redisTest(){
        List<Customer> list = customerMapper.selectList(null);
        redisCache.setCacheList("mylist",list);
        List<Object> mylist = redisCache.getCacheList("mylist");
        System.out.println(mylist);
    }
}
