package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.mapper.ManusortMapper;
import com.example.management.pojo.po.Manu;
import com.example.management.mapper.ManuMapper;
import com.example.management.pojo.po.Manusort;
import com.example.management.pojo.vo.AllManus;
import com.example.management.pojo.vo.SimpleManu;
import com.example.management.service.ManuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.CommonResult;
import com.example.management.utils.MyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zerowo
 * @since 2023-03-28
 */
@Service
public class ManuServiceImpl extends ServiceImpl<ManuMapper, Manu> implements ManuService {
    @Autowired(required = false)
    private ManuMapper manuMapper;

    @Autowired(required = false)
    private ManusortMapper manusortMapper;


    @Override
    public CommonResult allManu() {
        List<Manusort> manusorts = manusortMapper.selectList(null);
//        List<List> lists = null;
//        for(Manusort manusort : manusorts){
//            QueryWrapper wrapper = new QueryWrapper();
//            wrapper.eq("sortId",manusort.getSortId());
//            List list = manuMapper.selectList(wrapper);
//            list.add(manusort.getSortName());
//            if(lists != null){
//                lists.add(list);
//            }
//        }
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("sortId",0);
//        List<Manu> manus = manuMapper.selectList(wrapper);
//        for(Manu manu : manus){
//            List<Manu> mymanu = null;
//            if(mymanu == null){
//                mymanu.add(0,manu);
//            }
//            else{
//                mymanu.add(manu);
//            }
//            lists.add(mymanu);
//        }
        AllManus allManus = new AllManus();
        Map<String,List> maps = new HashMap<>();
        MyConverter converter = new MyConverter();
        for(Manusort manusort : manusorts){
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("sortId",manusort.getSortId());
            List<Manu> list = manuMapper.selectList(wrapper);
            maps.put(manusort.getSortName(),converter.manusToSimpleManus(list));
        }
        allManus.setLists(maps);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("sortId",0);
        List<Manu> list = manuMapper.selectList(wrapper);
        allManus.setManus(converter.manusToSimpleManus(list));
        return CommonResult.success(allManus);
    }
}
