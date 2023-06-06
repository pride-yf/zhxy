package com.example.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.zhxy.pojo.Clazz;
import com.example.zhxy.pojo.Student;
import com.example.zhxy.service.ClazzService;
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
@Api(tags="班级控制器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @ApiOperation("根据学生回显查询全部班级信息")
    @GetMapping("/getClazzs")
    public Result getClazzs(){
        List<Clazz> clazzs = clazzService.getClazzs();
        return Result.ok(clazzs);
    }

    @ApiOperation("删除和批量删除班级信息")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@ApiParam("需要删除的班级id集合")@RequestBody List<Integer> ids){
        clazzService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("分页展示获取班级信息")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(@ApiParam("分页查询的页码数")@PathVariable("pageNo") Integer pageNo,
                                 @ApiParam("分页查询的页码大小")@PathVariable("pageSize") Integer pageSize,
                                 @ApiParam("查询条件") Clazz clazz){
        Page<Clazz> pageParam = new Page<Clazz>(pageNo,pageSize);

        IPage<Clazz> page = clazzService.getClazzs(pageParam,clazz);

        return Result.ok(page);
    }

    @ApiOperation("新增或者修改班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrupdates(@ApiParam("Json格式的查询条件")@RequestBody Clazz clazz){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }
}
