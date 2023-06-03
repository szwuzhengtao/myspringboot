package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.pojo.po.Emotion;
import com.example.management.mapper.EmotionMapper;
import com.example.management.service.EmotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zerowo
 * @since 2023-05-22
 */
@Service
public class EmotionServiceImpl extends ServiceImpl<EmotionMapper, Emotion> implements EmotionService {
    @Autowired(required = false)
    EmotionMapper emotionMapper;

    @Override
    public CommonResult selectEmotion(String customerId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("customerId",customerId);
        List list = emotionMapper.selectList(wrapper);
        return CommonResult.success(list);
    }

    @Override
    public CommonResult addEmotion(Emotion emotion) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("customerId",emotion.getCustomerId());
        wrapper.eq("description",emotion.getDescription());
        if(emotionMapper.selectCount(wrapper) > 0){
            return CommonResult.error(400,"描述重复");
        }
        emotionMapper.insert(emotion);
        return CommonResult.success();
    }

    @Override
    public CommonResult deleteEmotion(Emotion emotion) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("customerId",emotion.getCustomerId());
        wrapper.eq("description",emotion.getDescription());
        emotionMapper.delete(wrapper);
        return CommonResult.success();
    }
}
