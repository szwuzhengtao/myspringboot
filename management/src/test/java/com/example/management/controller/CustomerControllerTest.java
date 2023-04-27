package com.example.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.mapper.CustomerMapper;
import com.example.management.mapper.LogMapper;
import com.example.management.pojo.po.Customer;
import com.example.management.service.CustomerService;
import com.example.management.utils.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class CustomerControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private CustomerController customerController;
    @Autowired
    private CustomerService customerService;
    @Autowired(required = false)
    private CustomerMapper customerMapper;
    @Autowired(required = false)
    private LogMapper logMapper;
    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    /**在http级别上测试
     * 模拟拥有权限的用户，测试customer/select
     * */
    @Test
    @WithMockUser(authorities={"获取客户信息"})
    void selectAllWithAuthority() throws Exception {
        String response=mvc.perform(post("http://localhost:9090/customer/select"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
        ;
        System.out.println(response);
    }
    /**在方法级别上测试
     * 模拟无权限用户，测试customer/select
     * */
    @Test
    @WithMockUser()
    void selectAll()  {
        Assertions.assertThrows( AccessDeniedException.class,()->customerController.selectAll());
    }

    /**在方法级别上测试
     * */
    @Test
    @WithMockUser(authorities = {"添加与修改客户"})
    void addCustomerWithAuthority() throws Exception {
        Customer test_cusomer=new Customer();
        String email= String.valueOf(new Random().nextInt(10000))+"@qq.com";//随机生成一个邮箱
        test_cusomer.setCustomerEmail(email);
        test_cusomer.setCustomerName("kqn");
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMzI3NjdkZDcxN2Y0MTI3YTBmN2ZiMjRkZGQzMmI2OSIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODE2NTM2OTAsImV4cCI6MTY4MTY2NDQ5MH0.TRX8EVB2CJ0590XSKno3uUvZp5v3ZM_M7rTPeh1MO_o";
        customerController.addCustomer(test_cusomer,token);//调用controller
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("customerId",test_cusomer.getCustomerId());
        Customer customer = customerMapper.selectOne(wrapper);//查询数据库，看是否添加成功
        Assertions.assertNotNull(customer);
        test_cusomer.setCustomerName("kqn2");//测试相同邮箱的不同客户，是否能添加成功
        CommonResult result= customerController.addCustomer(test_cusomer,token);
        Assertions.assertEquals(result.getCode(),400);
        customerMapper.delete(wrapper);//数据库删除测试用户
    }
    @Test
    @WithMockUser
    void addCustomer() {
        //断言拒绝访问异常
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMzI3NjdkZDcxN2Y0MTI3YTBmN2ZiMjRkZGQzMmI2OSIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODE2NTM2OTAsImV4cCI6MTY4MTY2NDQ5MH0.TRX8EVB2CJ0590XSKno3uUvZp5v3ZM_M7rTPeh1MO_o";
        Assertions.assertThrows(AccessDeniedException.class,()-> customerController.addCustomer(new Customer(32823,"asd","123@qq.com","male","","","","","","",""),token));
    }

    @Test
    @WithMockUser(authorities = {"删除客户"})
    void deleteCustomerWithAuthority() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMzI3NjdkZDcxN2Y0MTI3YTBmN2ZiMjRkZGQzMmI2OSIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODE2NTM2OTAsImV4cCI6MTY4MTY2NDQ5MH0.TRX8EVB2CJ0590XSKno3uUvZp5v3ZM_M7rTPeh1MO_o";
        CommonResult result=customerController.deleteCustomer(32823,token);

    }
    @Test
    @WithMockUser()
    void deleteCustomer() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMzI3NjdkZDcxN2Y0MTI3YTBmN2ZiMjRkZGQzMmI2OSIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODE2NTM2OTAsImV4cCI6MTY4MTY2NDQ5MH0.TRX8EVB2CJ0590XSKno3uUvZp5v3ZM_M7rTPeh1MO_o";
        Assertions.assertThrows(AccessDeniedException.class,()->customerController.deleteCustomer(32823,token));
    }
    @Test
    @WithMockUser(authorities = {"获取客户信息"})
    void selectById() {
        CommonResult result=customerController.selectById(32823);
        System.out.println(result.getCode());
        System.out.println(result.getMessage());
        System.out.println(result.getData());
    }

    @Test
    @WithMockUser(authorities = {"添加与修改客户"})
    void updateCustomerWithAuthority() {
        Customer test_cusomer=new Customer();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMzI3NjdkZDcxN2Y0MTI3YTBmN2ZiMjRkZGQzMmI2OSIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODE2NTM2OTAsImV4cCI6MTY4MTY2NDQ5MH0.TRX8EVB2CJ0590XSKno3uUvZp5v3ZM_M7rTPeh1MO_o";
        customerController.updateCustomer(test_cusomer,token);
    }
    @Test
    @WithMockUser
    void updateCustomer(){
        Customer test_cusomer=new Customer();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMzI3NjdkZDcxN2Y0MTI3YTBmN2ZiMjRkZGQzMmI2OSIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODE2NTM2OTAsImV4cCI6MTY4MTY2NDQ5MH0.TRX8EVB2CJ0590XSKno3uUvZp5v3ZM_M7rTPeh1MO_o";
        Assertions.assertThrows(AccessDeniedException.class,()->customerController.updateCustomer(test_cusomer,token));
    }

    @Test
    @WithMockUser(authorities = {"添加与修改客户"})
    @Transactional
    @Rollback()
    void addLog() {  //测试添加日志
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RequestBuilder request = post("http://localhost:9090/customer/add")
                .param("customerName","lisi")
                .param("customerEmail","szu.szu.edu.cn")
                .param("customerGender","男")
                .param("customerAddress","szu")
                .param("customerJob","教师")
                .param("customerPhone","141565195")
                .param("chargeStaff","小明")
                .param("customerQQ","24985321")
                .param("nickname","xiaohu")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMzI3NjdkZDcxN2Y0MTI3YTBmN2ZiMjRkZGQzMmI2OSIsInN1YiI6ImFiYyIsImlzcyI6Ind0ZmttankiLCJpYXQiOjE2ODE2NTM2OTAsImV4cCI6MTY4MTY2NDQ5MH0.TRX8EVB2CJ0590XSKno3uUvZp5v3ZM_M7rTPeh1MO_o");
        try{
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            CommonResult<?> result = mapper.readerFor(CommonResult.class).readValue(response);
            System.out.println(result);
            Assertions.assertEquals(result.getCode(), 200);
            QueryWrapper wrapper = new QueryWrapper("customerName","lisi");
            List list = logMapper.selectList(wrapper);
            System.out.println(list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}