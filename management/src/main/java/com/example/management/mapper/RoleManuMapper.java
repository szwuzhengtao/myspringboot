package com.example.management.mapper;

import com.example.management.pojo.po.Manu;
import com.example.management.pojo.po.RoleManu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zerowo
 * @since 2023-03-28
 */
public interface RoleManuMapper extends BaseMapper<RoleManu> {
    @Select("select manuId,manuName from role_manu where roleId = #{roleId} and state = #{state}")
    List<Manu> selectManus(Integer roleId, Integer state);
}
