package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.mapper.StaffMapper;
import com.example.management.pojo.Notice;
import com.example.management.mapper.NoticeMapper;
import com.example.management.pojo.Staff;
import com.example.management.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zerowo
 * @since 2023-04-15
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired(required = false)
    public NoticeMapper noticeMapper;

    @Autowired(required = false)
    public StaffMapper staffMapper;

    @Override
    public CommonResult addNotice(Notice notice) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        notice.setTime(sdf.format(System.currentTimeMillis()));
        noticeMapper.insert(notice);
        return CommonResult.success();
    }

    @Override
    public CommonResult myNotice(String staffId) {
        QueryWrapper wrapper = new QueryWrapper();
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper.eq("staffId",staffId);
        Staff staff = staffMapper.selectOne(wrapper);
        wrapper1.eq("department",staff.getDepartment());
        wrapper1.or();
        wrapper1.eq("department","所有部门");
        List list = noticeMapper.selectList(wrapper1);
        return CommonResult.success(list);
    }
}
