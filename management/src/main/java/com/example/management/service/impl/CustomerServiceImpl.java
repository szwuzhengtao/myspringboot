package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.domain.LoginStaff;
import com.example.management.mapper.DeletedMapper;
import com.example.management.mapper.LogMapper;
import com.example.management.mapper.StaffMapper;
import com.example.management.pojo.Customer;
import com.example.management.mapper.CustomerMapper;
import com.example.management.pojo.Deleted;
import com.example.management.pojo.Log;
import com.example.management.pojo.Staff;
import com.example.management.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.CommonResult;
import com.example.management.utils.JwtUtil;
import com.example.management.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Autowired(required = false)
    private CustomerMapper customerMapper;

    @Autowired(required = false)
    private StaffMapper staffMapper;

    @Autowired(required = false)
    private LogMapper logMapper;

    @Autowired(required = false)
    private DeletedMapper deletedMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public CommonResult selectAll() {
        List<Customer> list = customerMapper.selectList(null);
        return CommonResult.success(list);
    }

    @Override
    public CommonResult addCustomer(Customer customer, String token) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("customerEmail",customer.getCustomerEmail());
        if(customerMapper.selectCount(wrapper) > 0){
            return CommonResult.error(400,"邮箱重复");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        customer.setCustomerJointime(sdf.format(System.currentTimeMillis()));
        customerMapper.insert(customer);
        Claims claims = null;
        String userid = null;
        try {
            claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userid);
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("staffAccount",userid);
        Staff staff = staffMapper.selectOne(wrapper1);
        Customer customer1 = customerMapper.selectOne(wrapper);
        Log log = new Log();
        log.setStaffId(staff.getStaffId());
        log.setStaffName(staff.getStaffName());
        log.setCustomerId(customer1.getCustomerId());
        log.setCustomerName(customer1.getCustomerName());
        log.setOperation("添加");
        log.setTime(sdf.format(System.currentTimeMillis()));
        logMapper.insert(log);
        return CommonResult.success();
    }


    @Override
    public CommonResult deleteCustomer(int customerId, String token) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("customerId",customerId);
        if(customerMapper.selectCount(wrapper) == 0){
            return CommonResult.error(400,"用户不存在");
        }
        Customer customer = customerMapper.selectOne(wrapper);
        customerMapper.delete(wrapper);
        Claims claims = null;
        String userid = null;
        try {
            claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(userid);
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("staffAccount",userid);
        Staff staff = staffMapper.selectOne(wrapper1);
        Log log = new Log();
        Deleted deleted = new Deleted();
        log.setStaffId(staff.getStaffId());
        log.setStaffName(staff.getStaffName());
        log.setCustomerId(customer.getCustomerId());
        log.setCustomerName(customer.getCustomerName());
        log.setOperation("删除");
        log.setTime(sdf.format(System.currentTimeMillis()));
        deleted.setCustomerId(customer.getCustomerId());
        deleted.setCustomerName(customer.getCustomerName());
        deleted.setCustomerAddress(customer.getCustomerAddress());
        deleted.setCustomerEmail(customer.getCustomerEmail());
        deleted.setCustomerGender(customer.getCustomerGender());
        deleted.setCustomerPhone(customer.getCustomerPhone());
        deleted.setChargeStaff(customer.getChargeStaff());
        deleted.setCustomerQQ(customer.getCustomerQQ());
        deleted.setNickname(customer.getNickname());
        deleted.setCustomerJointime(customer.getCustomerJointime());
        deleted.setDeleteTime(sdf.format(System.currentTimeMillis()));
        deleted.setStaffId(staff.getStaffId());
        deleted.setStaffName(staff.getStaffName());
        logMapper.insert(log);
        deletedMapper.insert(deleted);
        return CommonResult.success();
    }

    @Override
    public CommonResult selectById(int customerId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("customerId",customerId);
        Customer customer = customerMapper.selectOne(wrapper);
        if(customer == null){
            return CommonResult.error(400,"用户不存在");
        }
        return CommonResult.success(customer);
    }

    @Override
    public CommonResult updateCustomer(Customer customer, String token) {
        if(customer == null){
            return CommonResult.error(400,"用户不存在");
        }
        customerMapper.updateById(customer);
        Claims claims = null;
        String userid = null;
        try {
            claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(userid);
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("staffAccount",userid);
        Staff staff = staffMapper.selectOne(wrapper1);
        Log log = new Log();
        log.setStaffId(staff.getStaffId());
        log.setStaffName(staff.getStaffName());
        log.setCustomerId(customer.getCustomerId());
        log.setCustomerName(customer.getCustomerName());
        log.setOperation("修改");
        log.setTime(sdf.format(System.currentTimeMillis()));
        logMapper.insert(log);
        return CommonResult.success();
    }
}
