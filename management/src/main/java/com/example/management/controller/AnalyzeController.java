package com.example.management.controller;

import com.example.management.service.AnalyzeService;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
@CrossOrigin
public class AnalyzeController {
    @Autowired
    private AnalyzeService analyzeService;

    @PreAuthorize("hasAuthority('数据分析')")
    @PostMapping("/home")
    public CommonResult analyzeHome(){
        return analyzeService.analyzeHome();
    }
}
