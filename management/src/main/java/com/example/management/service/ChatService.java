package com.example.management.service;

import com.example.management.pojo.po.Chat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.utils.CommonResult;

public interface ChatService extends IService<Chat> {

    CommonResult selectAll();
}
