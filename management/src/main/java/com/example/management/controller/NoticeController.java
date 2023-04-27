package com.example.management.controller;


import com.example.management.pojo.po.Notice;
import com.example.management.service.NoticeService;
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
 * @since 2023-04-15
 */
@RestController
@RequestMapping("/notice")
@CrossOrigin
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @PreAuthorize("hasAuthority('发布公告')")
    @PostMapping("/add")
    public CommonResult addNotice(Notice notice){
        return noticeService.addNotice(notice);
    }

    @PreAuthorize("hasAnyAuthority('获取公告')")
    @PostMapping("/my")
    public CommonResult myNotice(String staffId){
        return noticeService.myNotice(staffId);
    }

    @PreAuthorize("hasAuthority('获取公告')")
    @PostMapping("/select")
    public CommonResult selectNotice(String department){
        return noticeService.selectNotice(department);
    }
}

