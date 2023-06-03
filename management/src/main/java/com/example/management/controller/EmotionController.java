package com.example.management.controller;


import com.example.management.pojo.po.Emotion;
import com.example.management.service.EmotionService;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zerowo
 * @since 2023-05-22
 */
@RestController
@RequestMapping("/emotion")
@CrossOrigin
public class EmotionController {
    @Autowired
    EmotionService emotionService;

    @PreAuthorize("hasAuthority('查找聊天记录')")
    @PostMapping("/select")
    public CommonResult selectEmotion(String customerId){
        return emotionService.selectEmotion(customerId);
    }

    @PreAuthorize("hasAuthority('查找聊天记录')")
    @PostMapping("/add")
    public CommonResult addEmotion(Emotion emotion){
        return emotionService.addEmotion(emotion);
    }

    @PreAuthorize("hasAuthority('查找聊天记录')")
    @PostMapping("/delete")
    public CommonResult deleteEmotion(Emotion emotion){
        return emotionService.deleteEmotion(emotion);
    }
}

