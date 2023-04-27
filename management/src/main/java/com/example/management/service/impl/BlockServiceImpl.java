package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.pojo.ro.RecordIds;
import com.example.management.mapper.RecordMapper;
import com.example.management.mapper.RecordblockMapper;
import com.example.management.pojo.po.Block;
import com.example.management.mapper.BlockMapper;
import com.example.management.pojo.po.Record;
import com.example.management.pojo.po.Recordblock;
import com.example.management.service.BlockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zerowo
 * @since 2023-04-19
 */
@Service
public class BlockServiceImpl extends ServiceImpl<BlockMapper, Block> implements BlockService {
    @Autowired(required = false)
    private BlockMapper blockMapper;

    @Autowired(required = false)
    private RecordblockMapper recordblockMapper;
    
    @Autowired(required = false)
    private RecordMapper recordMapper;

    @Override
    public CommonResult recordBlock(@RequestBody RecordIds recordIds) {
        Block block = new Block(recordIds.getStaffId(),recordIds.getNote());
        System.out.println("blockId:" + block.getBlockId());
        blockMapper.insert(block);
        Integer blockId = blockMapper.selectMax();
        for(Integer recordId : recordIds.getRecordIds()){
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.select("personId").eq("recordId", recordId);
            Record record = recordMapper.selectOne(wrapper);
            recordblockMapper.insert(new Recordblock(blockId,recordId,record.getPersonId()));
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult selectCustomerBlock(String personId) {
        List<Integer> list = recordblockMapper.selectBlock(personId);
        List<Block> blocks = blockMapper.selectBatchIds(list);
        return CommonResult.success(blocks);
    }

    @Override
    public CommonResult blockDetails(Integer blockId) {
        List<Integer> list = recordblockMapper.selectRecord(blockId);
        List<Record> records = recordMapper.selectBatchIds(list);
        return CommonResult.success(records);
    }
}
