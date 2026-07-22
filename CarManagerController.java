package com.carrent.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.carrent.common.Result;
import com.carrent.dto.CarQueryDTO;
import com.carrent.dto.StoreQueryDTO;
import com.carrent.entity.Car;
import com.carrent.entity.Store;
import com.carrent.entity.User;
import com.carrent.service.ICarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 车辆表 前端控制器
 * </p>
 *
 * @author bruceliu
 * @since 2026-07-20
 */
@Controller
@RequestMapping("/admin/car")
@ResponseBody
@Tag(name = "车辆管理控制器接口", description = "车辆管理控制器接口各种方法")
@Slf4j
public class CarManagerController {

    @Autowired
    ICarService carService;

    @Operation(summary = "分页查询车辆列表", description = "分页查询车辆列表")
    // 方法级注解
    @GetMapping("/list")
    public Result<IPage<Car>> list(CarQueryDTO carQueryDTO){
        log.info("分页查询车辆列表：{}", carQueryDTO);
        IPage<Car> page = carService.queryPage(carQueryDTO);
        return Result.success(page);
    }

    @GetMapping("/listAll")
    @Operation(summary = "查询所有车辆", description = "查询所有车辆")
    public Result<List<Car>> listAll(){
        // 创建查询条件
        QueryWrapper<Car> qw = new QueryWrapper<>();
        // 设置查询条件
        qw.eq("del", 0); // 删除标志为0  查询del=0
        List<Car> cars = carService.list(qw);
        return Result.success(cars);
    }

    @Operation(summary = "根据ID查询车辆", description = "根据ID查询车辆")
    @GetMapping("/get/{id}")
    public Result<Car> getOne(@PathVariable("id") Integer id){
        Car car = carService.getById(id);
        return Result.success(car);
    }

    @Operation(summary = "根据ID删除车辆", description = "根据ID删除车辆")
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable("id") Long id){
        Car car=new Car();
        car.setId(id);
        car.setDel(1); // 删除标志为1
        carService.updateById(car);
        return Result.success("删除成功");
    }

    @Operation(summary = "根据ID修改车辆", description = "根据ID修改车辆")
    @PutMapping("/update")
    public Result<String> update(@RequestBody Car car){
        carService.updateById(car);
        return Result.success("修改成功");
    }

    @Operation(summary = "添加车辆", description = "添加车辆")
    @PostMapping("/add")
    public Result<String> add(@RequestBody Car car){
        carService.save(car);
        return Result.success("添加成功");
    }

}
