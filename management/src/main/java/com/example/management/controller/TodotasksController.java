package com.example.management.controller;


import com.example.management.pojo.po.Todotasks;
import com.example.management.service.TodotasksService;
import com.example.management.utils.CommonResult;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zerowo
 * @since 2023-05-07
 */
@RestController
@RequestMapping("/todotasks")
@CrossOrigin
public class TodotasksController {
    @Autowired
    private TodotasksService todotasksService;

    @PreAuthorize("hasAuthority('待办任务')")
    @PostMapping("/add")
    public CommonResult addTodo(@RequestBody Todotasks todotasks){
        return todotasksService.addTodo(todotasks);
    }

    @PreAuthorize("hasAuthority('待办任务')")
    @PostMapping("/all")
    public CommonResult allTodo(@RequestBody Integer staffId){
        return todotasksService.allTodo(staffId);
    }

    @PreAuthorize("hasAuthority('待办任务')")
    @PostMapping("/select/state")
    public CommonResult selectTodoByState(@RequestBody Integer staffId, String state){
        return todotasksService.selectTodoByState(staffId,state);
    }

    @PreAuthorize("hasAuthority('待办任务')")
    @PostMapping("/change/state")
    public CommonResult changeTodoState(@RequestBody Integer taskId, String state){
        return todotasksService.changeTodoState(taskId,state);
    }
}

