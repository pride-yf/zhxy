package com.example.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.zhxy.pojo.Admin;
import com.example.zhxy.service.AdminService;
import com.example.zhxy.util.MD5;
import com.example.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: yf
 * @date: 2023/3/30 - 15:38
 * @description:
 */
@Api(tags="管理员控制器")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //getAllAdmin/1/3
    @ApiOperation("获取所有管理员信息")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(@ApiParam("页码数") @PathVariable("pageNo")Integer pageNo,
                              @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
                              @ApiParam("管理员姓名") String name){
        Page<Admin> page = new Page<>(pageNo,pageSize);

        IPage<Admin> pageRs = adminService.getAllAdmin(page,name);

        return Result.ok(pageRs);
    }

    @ApiOperation("删除或者批量删除管理员信息")
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(@ApiParam("删除对象的id的json格式集") @RequestBody List<Integer> ids){
        adminService.removeByIds(ids);
        return  Result.ok();
    }

    @ApiOperation("增加或者修改管理员信息")
    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(@ApiParam("新增或者需要修改的管理员的json格式信息")
                                        @RequestBody Admin admin){
        if(admin.getPassword() != null && !admin.getPassword().equals("")){
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }
        adminService.saveOrUpdate(admin);
        return Result.ok();
    }
}
