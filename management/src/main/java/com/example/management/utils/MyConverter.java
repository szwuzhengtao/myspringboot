package com.example.management.utils;

import com.example.management.pojo.po.Customer;
import com.example.management.pojo.po.Deleted;
import com.example.management.pojo.po.Log;
import com.example.management.pojo.po.Staff;

import java.text.SimpleDateFormat;

public class MyConverter {
    private SimpleDateFormat sdf;

    public MyConverter(){
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public Deleted toDeleted(Customer customer, Staff staff){
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Deleted deleted = new Deleted();
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
        return deleted;
    }

    public Log toLog(Customer customer, Staff staff, String operate){
        Log log = new Log();
        log.setStaffId(staff.getStaffId());
        log.setStaffName(staff.getStaffName());
        log.setCustomerId(customer.getCustomerId());
        log.setCustomerName(customer.getCustomerName());
        log.setOperation(operate);
        log.setTime(sdf.format(System.currentTimeMillis()));
        return log;
    }

    public Log deletedToLog(Customer customer, Deleted deleted, String operate){
        Log log = new Log();
        log.setStaffId(deleted.getStaffId());
        log.setStaffName(deleted.getStaffName());
        log.setCustomerId(customer.getCustomerId());
        log.setCustomerName(customer.getCustomerName());
        log.setOperation(operate);
        log.setTime(sdf.format(System.currentTimeMillis()));
        return log;
    }

    public Customer deletedToCustomer(Deleted deleted){
        Customer customer = new Customer();
        customer.setCustomerName(deleted.getCustomerName());
        customer.setCustomerEmail(deleted.getCustomerEmail());
        customer.setCustomerAddress(deleted.getCustomerAddress());
        customer.setChargeStaff(deleted.getChargeStaff());
        customer.setCustomerGender(deleted.getCustomerGender());
        customer.setCustomerJob(deleted.getCustomerJob());
        customer.setCustomerQQ(deleted.getCustomerQQ());
        customer.setNickname(deleted.getNickname());
        customer.setCustomerJointime(sdf.format(System.currentTimeMillis()));
        return customer;
    }
}
