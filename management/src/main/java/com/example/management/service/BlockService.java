package com.example.management.service;

import com.example.management.pojo.ro.RecordIds;
import com.example.management.pojo.po.Block;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.utils.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zerowo
 * @since 2023-04-19
 */
public interface BlockService extends IService<Block> {

    CommonResult recordBlock(RecordIds recordIds);

    CommonResult selectCustomerBlock(String personId);

    CommonResult blockDetails(Integer blockId);
}
