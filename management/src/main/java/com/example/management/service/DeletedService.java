package com.example.management.service;

import com.example.management.pojo.po.Deleted;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.utils.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zerowo
 * @since 2023-04-16
 */
public interface DeletedService extends IService<Deleted> {

    CommonResult restore(Deleted deleted);

    CommonResult allDelete(String staffId);
}
