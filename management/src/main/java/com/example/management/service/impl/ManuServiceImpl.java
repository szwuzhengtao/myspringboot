package com.example.management.service.impl;

import com.example.management.pojo.Manu;
import com.example.management.mapper.ManuMapper;
import com.example.management.service.ManuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public CommonResult allManu() {
        List<Manu> manus = manuMapper.selectList(null);
        return CommonResult.success(manus);
    }
}
