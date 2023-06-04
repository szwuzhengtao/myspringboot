package com.example.management.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.mapper.MemberMapper;
import com.example.management.pojo.po.*;
import com.example.management.pojo.vo.ChatShow;
import com.example.management.pojo.vo.RecordDetails;
import com.example.management.pojo.vo.SimpleManu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyConverter {
    private SimpleDateFormat sdf;

    @Autowired(required = false)
    private MemberMapper memberMapper;

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

    public List<SimpleManu> manusToSimpleManus(List<Manu> manus){
        List<SimpleManu> simpleManus = new ArrayList<>();
        for(Manu manu : manus){
            simpleManus.add(new SimpleManu(manu));
        }
        return simpleManus;
    }

    public List<RecordDetails> recordCollection(List<Record> records){
        List<RecordDetails> recordDetails = new ArrayList<>();
        for(Record record : records){
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("memberQQ",record.getPersonId());
            Member member = memberMapper.selectOne(wrapper);
            RecordDetails recordDetail = new RecordDetails();
            recordDetail.setRecordId(record.getRecordId());
            recordDetail.setPersonId(record.getPersonId());
            recordDetail.setNickname(member.getNickname());
            recordDetail.setAvatarURL(member.getAvatarURL());
            recordDetail.setContent(record.getContent());
            recordDetail.setChatId(record.getChatId());
            recordDetail.setChatName(record.getChatName());
            recordDetail.setIsCustomer(member.getIsCustomer());
            recordDetail.setTime(record.getTime());
            recordDetails.add(recordDetail);
        }
        return recordDetails;
    }

    public RecordDetails recordPlusMember(Record record, Member member){
        RecordDetails recordDetail = new RecordDetails();
        recordDetail.setRecordId(record.getRecordId());
        recordDetail.setPersonId(record.getPersonId());
        recordDetail.setNickname(member.getNickname());
        recordDetail.setAvatarURL(member.getAvatarURL());
        recordDetail.setContent(record.getContent());
        recordDetail.setChatId(record.getChatId());
        recordDetail.setChatName(record.getChatName());
        recordDetail.setIsCustomer(member.getIsCustomer());
        recordDetail.setTime(record.getTime());
        return recordDetail;
    }

    public ChatShow showChat(Chat chat,Record record){
        ChatShow chatShow = new ChatShow();
        chatShow.setChatId(chat.getChatId());
        chatShow.setChatName(chat.getChatName());
        chatShow.setNumber(chat.getNumber());
        chatShow.setBuildTime(chat.getBuildTime());
        chatShow.setDegree(chat.getDegree());
        chatShow.setRecent(record.getContent());
        return chatShow;
    }
}
