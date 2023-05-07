package com.example.management.service;

import com.example.management.pojo.po.Todotasks;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.utils.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zerowo
 * @since 2023-05-07
 */
public interface TodotasksService extends IService<Todotasks> {

    CommonResult addTodo(Todotasks todotasks);

    CommonResult allTodo(Integer staffId);

    CommonResult selectTodoByState(Integer staffId, String state);

    CommonResult changeTodoState(Integer taskId, String state);
}
