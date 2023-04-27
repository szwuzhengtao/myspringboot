package com.example.management.service;

import com.example.management.pojo.po.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.utils.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zerowo
 * @since 2023-04-15
 */
public interface NoticeService extends IService<Notice> {

    CommonResult addNotice(Notice notice);

    CommonResult myNotice(String staffId);

    CommonResult selectNotice(String department);
}
