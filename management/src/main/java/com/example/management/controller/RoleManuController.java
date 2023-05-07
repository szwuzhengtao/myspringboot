package com.example.management.controller;


import com.example.management.pojo.po.RoleManu;
import com.example.management.service.RoleManuService;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zerowo
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/role-manu")
@CrossOrigin
public class RoleManuController {
    @Autowired
    private RoleManuService roleManuService;

    @PreAuthorize("hasAuthority('设置权限')")
    @PostMapping("/add")
    public CommonResult setRoleManu(@RequestBody int roleId, int manuId){
        return roleManuService.setRoleManu(roleId,manuId);
    }

    @PreAuthorize("hasAuthority('设置权限')")
    @PostMapping("/select")
    public CommonResult selectRoleManu(@RequestBody String roleId){
        return roleManuService.selectRoleManu(roleId);
    }

    @PreAuthorize("hasAuthority('设置权限')")
    @PostMapping("/delete")
    public CommonResult deleteRoleManu(@RequestBody RoleManu roleManu){
        return roleManuService.deleteRoleManu(roleManu);
    }
}

