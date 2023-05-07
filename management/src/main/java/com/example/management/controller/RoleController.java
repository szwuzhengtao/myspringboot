package com.example.management.controller;


import com.example.management.pojo.po.Role;
import com.example.management.service.RoleService;
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
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('设置权限')")
    @PostMapping("/add")
    public CommonResult newRole(@RequestBody Role role){
        return roleService.newRole(role);
    }

    @PreAuthorize("hasAuthority('设置权限')")
    @PostMapping("/all")
    public CommonResult allRole(){
        return roleService.allRole();
    }

    @PreAuthorize("hasAuthority('设置权限')")
    @PostMapping("/select")
    public CommonResult selectRole(@RequestBody int staffId){
        return roleService.selectRole(staffId);
    }
}

