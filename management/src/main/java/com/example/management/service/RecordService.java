package com.example.management.service;

import com.example.management.pojo.ro.RecordIds;
import com.example.management.pojo.po.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.utils.CommonResult;

public interface RecordService extends IService<Record> {

    CommonResult selectAll();

    CommonResult selectById(int customerId);

    CommonResult selectByChatId(int chatId);

    CommonResult selectByKey(int chatId, String key);

    CommonResult recordBlock(RecordIds recordIds);

    CommonResult keyWords(String personId);
}
