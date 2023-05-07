package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.mapper.CustomerMapper;
import com.example.management.mapper.LogMapper;
import com.example.management.pojo.po.Customer;
import com.example.management.pojo.po.Deleted;
import com.example.management.mapper.DeletedMapper;
import com.example.management.pojo.po.Log;
import com.example.management.service.DeletedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.CommonResult;
import com.example.management.utils.MyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zerowo
 * @since 2023-04-16
 */
@Service
public class DeletedServiceImpl extends ServiceImpl<DeletedMapper, Deleted> implements DeletedService {
    @Autowired(required = false)
    private DeletedMapper deletedMapper;

    @Autowired(required = false)
    private CustomerMapper customerMapper;

    @Autowired(required = false)
    private LogMapper logMapper;

    @Override
    public CommonResult restore(Deleted deleted) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("customerId",deleted.getCustomerId());
        Deleted deleted1 = deletedMapper.selectOne(wrapper);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MyConverter converter = new MyConverter();
        Customer customer = converter.deletedToCustomer(deleted1);
        Log log = converter.deletedToLog(customer,deleted1,"恢复");
        customerMapper.insert(customer);
        logMapper.insert(log);
        deletedMapper.deleteById(deleted1.getCustomerId());
        return CommonResult.success();
    }

    @Override
    public CommonResult allDelete(String staffId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("staffId",staffId);
        List list = deletedMapper.selectList(wrapper);
        return CommonResult.success(list);
    }
}
