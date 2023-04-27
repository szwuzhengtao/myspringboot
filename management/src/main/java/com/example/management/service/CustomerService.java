package com.example.management.service;

import com.example.management.pojo.po.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.utils.CommonResult;

public interface CustomerService extends IService<Customer> {

    CommonResult selectAll();

    CommonResult addCustomer(Customer customer, String token);

    CommonResult deleteCustomer(int customerId, String token);

    CommonResult selectById(int customerId);

    CommonResult updateCustomer(Customer customer, String token);
}
