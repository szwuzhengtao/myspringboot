package com.example.management.mapper;

import com.example.management.pojo.po.Block;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zerowo
 * @since 2023-04-19
 */
public interface BlockMapper extends BaseMapper<Block> {
    @Select("select max(blockId) from block")
    Integer selectMax();

}
