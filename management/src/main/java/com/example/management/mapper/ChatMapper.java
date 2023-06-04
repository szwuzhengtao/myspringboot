package com.example.management.mapper;

import com.example.management.pojo.po.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.management.pojo.po.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

}
