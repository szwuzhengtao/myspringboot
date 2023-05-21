package com.example.management.controller;


import com.example.management.pojo.ro.RecordIds;
import com.example.management.service.RecordService;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@CrossOrigin
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PreAuthorize("hasAuthority('查找聊天记录')")
    @PostMapping("/select")
    public CommonResult selectAll(){
        return recordService.selectAll();
    }

    @PreAuthorize("hasAnyAuthority('查找聊天记录')")
    @PostMapping("/select/customer")
    public CommonResult selectById(@RequestBody int personId){
        return recordService.selectById(personId);
    }

    @PreAuthorize("hasAnyAuthority('查找聊天记录')")
    @PostMapping("/select/chat")
    public CommonResult selectByChatId(@RequestBody int chatId){
        return recordService.selectByChatId(chatId);
    }

    @PreAuthorize("hasAuthority('查找聊天记录')")
    @PostMapping("/select/key")
    public CommonResult selectByKey(@RequestBody int chatId, String key){
        return recordService.selectByKey(chatId,key);
    }

    @PreAuthorize("hasAuthority('聊天记录分块')")
    @PostMapping("/block")
    public CommonResult recordBlock(@RequestBody RecordIds recordIds){
        return recordService.recordBlock(recordIds);
    }

    @PreAuthorize("hasAuthority('查找聊天记录')")
    @PostMapping("/key")
    public CommonResult keyWords(String personId){
        return recordService.keyWords(personId);
    }
}

