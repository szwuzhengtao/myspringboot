package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.pojo.po.Todotasks;
import com.example.management.mapper.TodotasksMapper;
import com.example.management.service.TodotasksService;
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
 * @since 2023-05-07
 */
@Service
public class TodotasksServiceImpl extends ServiceImpl<TodotasksMapper, Todotasks> implements TodotasksService {
    @Autowired(required = false)
    private TodotasksMapper todotasksMapper;

    @Override
    public CommonResult addTodo(Todotasks todotasks) {
        todotasksMapper.insert(todotasks);
        return CommonResult.success();
    }

    @Override
    public CommonResult allTodo(Integer staffId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("staffId",staffId);
        List list = todotasksMapper.selectList(wrapper);
        return CommonResult.success(list);
    }

    @Override
    public CommonResult selectTodoByState(Integer staffId, String state) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("staffId",staffId);
        wrapper.eq("state",state);
        List list = todotasksMapper.selectList(wrapper);
        return CommonResult.success(list);
    }

    @Override
    public CommonResult changeTodoState(Integer taskId, String state) {
        Todotasks todotasks = todotasksMapper.selectById(taskId);
        todotasks.setState(state);
        todotasksMapper.updateById(todotasks);
        return CommonResult.success();
    }
}
