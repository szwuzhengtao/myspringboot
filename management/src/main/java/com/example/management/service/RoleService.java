package com.example.management.service;

import com.example.management.pojo.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.utils.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zerowo
 * @since 2023-03-28
 */
public interface RoleService extends IService<Role> {

    CommonResult newRole(Role role);

    CommonResult allRole();

    CommonResult selectRole(int staffId);
}
