package com.example.management.controller;


import com.example.management.service.ManuService;
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
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/manu")
@CrossOrigin
public class ManuController {
    @Autowired
    private ManuService manuService;

    @PreAuthorize("hasAuthority('设置权限')")
    @PostMapping("/all")
    public CommonResult allManu(){
        return manuService.allManu();
    }
}

