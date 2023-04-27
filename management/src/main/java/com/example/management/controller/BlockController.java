package com.example.management.controller;


import com.example.management.pojo.ro.RecordIds;
import com.example.management.service.BlockService;
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
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/block")
@CrossOrigin
public class BlockController {
    @Autowired
    private BlockService blockService;

    @PreAuthorize("hasAuthority('聊天记录分块')")
    @PostMapping("/add")
    public CommonResult recordBlock(@RequestBody RecordIds recordIds){
        return blockService.recordBlock(recordIds);
    }

    @PreAuthorize("hasAuthority('查找聊天记录')")
    @PostMapping("/select/person")
    public CommonResult selectCustomerBlock(String personId){
        return blockService.selectCustomerBlock(personId);
    }

    @PreAuthorize("hasAuthority('查找聊天记录')")
    @PostMapping("/details")
    public CommonResult blockDetails(Integer blockId){
        return blockService.blockDetails(blockId);
    }
}

