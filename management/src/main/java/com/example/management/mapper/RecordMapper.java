package com.example.management.mapper;

import com.example.management.pojo.po.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    @Select("select * from record where chatId = #{chatId} order by recordId desc limit 1")
    Record selectRecord(String chatId);
}
