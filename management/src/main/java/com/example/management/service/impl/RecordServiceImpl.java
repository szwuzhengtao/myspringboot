package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.dto.RecordIds;
import com.example.management.mapper.BlockMapper;
import com.example.management.mapper.RecordblockMapper;
import com.example.management.pojo.Record;
import com.example.management.mapper.RecordMapper;
import com.example.management.pojo.Recordblock;
import com.example.management.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.CommonResult;
import com.example.management.utils.RedisCache;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired(required = false)
    private RecordMapper recordMapper;

    @Autowired(required = false)
    private RecordblockMapper recordblockMapper;

    @Autowired(required = false)
    private BlockMapper blockMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public CommonResult selectAll() {
        List<Record> records = recordMapper.selectList(null);
        return CommonResult.success(records);
    }

    @Override
    public CommonResult selectById(int customerId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("personId",customerId);
        List list = recordMapper.selectList(wrapper);
        return CommonResult.success(list);
    }

    @Override
    public CommonResult selectByChatId(int chatId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chatId",chatId);
        List list = recordMapper.selectList(wrapper);
        return CommonResult.success(list);
    }

    @Override
    public CommonResult selectByKey(int chatId, String key) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chatId",chatId);
        wrapper.like("content",key);
        List list = recordMapper.selectList(wrapper);
        return CommonResult.success(list);
    }

    @Override
    public CommonResult recordBlock(RecordIds recordIds) {
        return CommonResult.success();
    }
}
