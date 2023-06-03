package com.example.management.service;

import com.example.management.pojo.po.Emotion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.utils.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zerowo
 * @since 2023-05-22
 */
public interface EmotionService extends IService<Emotion> {

    CommonResult selectEmotion(String customerId);

    CommonResult addEmotion(Emotion emotion);

    CommonResult deleteEmotion(Emotion emotion);
}
