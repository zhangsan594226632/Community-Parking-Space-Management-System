package com.carrent.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carrent.common.Result;
import com.carrent.entity.User;
import com.carrent.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin/user")
@Tag(name = "用户管理控制器接口", description = "用户管理控制器接口各种方法")
public class UserManagerController {

    @Autowired
    UserService userService;

    @Operation(summary = "查询所有用户", description = "查询所有用户")
    @GetMapping("/list")
    public Result<List<User>> list(){
        // 创建查询条件
        QueryWrapper<User> qw = new QueryWrapper<>();
        // 设置查询条件
        qw.eq("del", 0); // 删除标志为0  查询del=0
        List<User> users = userService.list(qw);
        return Result.success(users);
    }

    @Operation(summary = "根据ID查询用户", description = "根据ID查询用户")
    @GetMapping("/get/{id}")
    public Result<User> getOne(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    @Operation(summary = "根据ID删除用户", description = "根据ID删除用户")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable("id") Integer id){
        User user=new User();
        user.setId(id);
        user.setDel(1); // 删除标志为1
        userService.updateById(user);
        return Result.success("删除成功");
    }

    @Operation(summary = "根据ID修改用户", description = "根据ID修改用户")
    @PutMapping("/update")
    public Result<String> update(@RequestBody User user){
        userService.updateById(user);
        return Result.success("修改成功");
    }

    @Operation(summary = "添加用户", description = "添加用户")
    @PostMapping("/add")
    public Result<String> add(@RequestBody User user){
        userService.save(user);
        return Result.success("添加成功");
    }

}
