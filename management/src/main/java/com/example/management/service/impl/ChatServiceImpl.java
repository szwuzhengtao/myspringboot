package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.mapper.RecordMapper;
import com.example.management.pojo.po.Chat;
import com.example.management.mapper.ChatMapper;
import com.example.management.pojo.po.Record;
import com.example.management.pojo.vo.ChatShow;
import com.example.management.service.ChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.CommonResult;
import com.example.management.utils.MyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {
    @Autowired(required = false)
    private ChatMapper chatMapper;

    @Autowired(required = false)
    private RecordMapper recordMapper;

    @Override
    public CommonResult selectAll() {  //查询所有群聊
        List<Chat> chats = chatMapper.selectList(null);
        List<ChatShow> chatShows = new ArrayList<>();
        MyConverter converter = new MyConverter();
        for(Chat chat : chats){
            Record record = recordMapper.selectRecord(chat.getChatId());
            ChatShow chatShow = converter.showChat(chat, record);
            chatShows.add(chatShow);
        }
        return CommonResult.success(chatShows);
    }
}
