package com.example.management.service;

import com.example.management.pojo.po.RoleManu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.pojo.ro.RoleManus;
import com.example.management.utils.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zerowo
 * @since 2023-03-28
 */
public interface RoleManuService extends IService<RoleManu> {

    CommonResult setRoleManu(int roleId, int manuId);

    CommonResult selectRoleManu(Integer roleId);

    CommonResult deleteRoleManu(RoleManu roleManu);

    CommonResult setAllRoleManu(RoleManus roleManus);
}
