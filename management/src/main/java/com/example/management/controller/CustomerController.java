package com.example.management.controller;


import com.example.management.pojo.po.Customer;
import com.example.management.service.CustomerService;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasAuthority('获取客户信息')")
    @PostMapping("/select")
    public CommonResult selectAll(){
        return customerService.selectAll();
    }

    @PreAuthorize("hasAuthority('添加与修改客户')")
    @PostMapping("/add")
    public CommonResult addCustomer(Customer customer, @RequestHeader("token") String token){
        return customerService.addCustomer(customer,token);
    }

    @PreAuthorize("hasAuthority('删除客户')")
    @PostMapping("/delete")
    public CommonResult deleteCustomer(int customerId, @RequestHeader("token") String token) {
        return customerService.deleteCustomer(customerId,token);
    }

    @PreAuthorize("hasAuthority('获取客户信息')")
    @PostMapping("/select/id")
    public CommonResult selectById(int customerId){
        return customerService.selectById(customerId);
    }

    @PreAuthorize("hasAuthority('添加与修改客户')")
    @PostMapping("/update")
    public CommonResult updateCustomer(Customer customer, @RequestHeader("token") String token){
        return customerService.updateCustomer(customer,token);
    }



}

