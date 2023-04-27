package com.example.management.mapper;

import com.example.management.pojo.po.Recordblock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zerowo
 * @since 2023-04-18
 */
public interface RecordblockMapper extends BaseMapper<Recordblock> {
    @Select("select distinct blockId from recordblock where personId = #{personId}")
    List<Integer> selectBlock(String personId);

    @Select("select recordId from recordblock where blockId = #{blockId}")
    List<Integer> selectRecord(Integer blockId);
}
