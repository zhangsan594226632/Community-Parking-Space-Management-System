package com.carrent.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.carrent.common.Result;
import com.carrent.dto.StoreQueryDTO;
import com.carrent.entity.Store;
import com.carrent.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

/**
 * StoreController类
 */
@Controller
@RequestMapping("/admin/store")
@ResponseBody
@Tag(name = "门店管理控制器接口", description = "门店管理控制器接口各种方法") // 类级注解，用于标识这个类是控制器，并提供标签和描述
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "成功"),
        @ApiResponse(responseCode = "404", description = "资源未找到"),
        @ApiResponse(responseCode = "500", description = "服务器错误")
})
@Slf4j
public class StoreManagerController {

    @Autowired
    StoreService storeService;

    @Operation(summary = "分页查询所有门店", description = "分页查询所有门店")
    // 方法级注解
    @GetMapping("/list")
    public Result<IPage<Store>> list(StoreQueryDTO storeQueryDTO){
        log.info("分页查询所有门店：{}", storeQueryDTO);
        IPage<Store> page = storeService.queryPage(storeQueryDTO);
        return Result.success(page);
    }

    @Operation(summary = "查询所有门店", description = "查询所有门店")
    // 方法级注解
    @GetMapping("/listAll")
    public Result<List<Store>> listAll(){
        //查询所有门店
        List<Store> storeList = storeService.selectAllStores();
        return Result.success(storeList);
    }

    @Operation(summary = "根据ID查询门店", description = "查询指定ID的门店")
    @GetMapping("/get/{id}")
    public Result<Store> get(@PathVariable("id") int storeId){
        //查询指定ID的门店
        Store store = storeService.selectStoreById(storeId);
        return Result.success(store);
    }

    @Operation(summary = "新增门店", description = "新增门店")
    @PostMapping("add")
    public Result<String> add(@RequestBody Store store){
        log.info("新增门店：{}", store);
        storeService.insertStore(store);
        return Result.success("新增成功！");
    }
    @Operation(summary = "更新门店", description = "更新门店")
    @PutMapping("update")
    public Result<String> update(@RequestBody Store store){
        storeService.updateStore(store);
        return Result.success("更新成功！");
    }

    @Operation(summary = "根据ID删除门店", description = "根据ID删除门店")
    @DeleteMapping("delete/{id}")
    public Result<String> delete(@PathVariable("id") int storeId){
        storeService.softDeleteStore(storeId);
        return Result.success("删除成功！");
    }

    @Operation(summary = "更新门店状态", description = "更新门店状态")
    @PutMapping("/{id}/status")
    public Result<String> updateStoreStatus(@PathVariable("id") Long id,Long status){
        Store store=new Store();
        store.setId(id);
        store.setStatus(status);
        storeService.updateById(store);
        return Result.success("更新成功！");
    }

}
