package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.mapper.ChatMapper;
import com.example.management.mapper.CustomerMapper;
import com.example.management.mapper.FollowupMapper;
import com.example.management.mapper.StaffMapper;
import com.example.management.pojo.vo.AnalyzeAll;
import com.example.management.service.AnalyzeService;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class AnalyzeServiceImpl implements AnalyzeService {
    @Autowired(required = false)
    private CustomerMapper customerMapper;

    @Autowired(required = false)
    private StaffMapper staffMapper;

    @Autowired(required = false)
    private FollowupMapper followupMapper;

    @Autowired(required = false)
    private ChatMapper chatMapper;

    @Override
    public CommonResult analyzeHome() {
        AnalyzeAll analyze = new AnalyzeAll();
        analyze.setCustomerNum(customerMapper.selectCount(null));
        analyze.setStaffNum(staffMapper.selectCount(null));
        analyze.setChatNum(chatMapper.selectCount(null));
        analyze.setFollowupNum(followupMapper.selectCount(null));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(System.currentTimeMillis());
        String[] month = {"-01-","-02-","-03-","-04-","-05-","-06-",
                          "-07-","-08-","-09-","-10-","-11-","-12-"};
        Integer[] followups = new Integer[12];
        for(int i = 0; i < 12; i++){
            month[i] = year + month[i];
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.like("entryTime",month[i]);
            followups[i] = followupMapper.selectCount(wrapper);
        }
        analyze.setFollowups(followups);
        analyze.setOtherNum(10);
        return CommonResult.success(analyze);
    }
}
