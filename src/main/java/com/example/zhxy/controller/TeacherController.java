package com.example.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.zhxy.pojo.Teacher;
import com.example.zhxy.service.TeacherService;
import com.example.zhxy.util.MD5;
import com.example.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: yf
 * @date: 2023/3/30 - 15:38
 * @description:
 */
@Api(tags="教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取所有的教师信息")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(  @ApiParam("页码数")  @PathVariable("pageNo") Integer pageNo,
                                @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
                                @ApiParam("教师名称") String name,
                                @ApiParam("班级名称") String clazzName
        ){
        Page<Teacher> page = new Page<>(pageNo,pageSize);
        IPage<Teacher> pageRs = teacherService.getTearchers(page,name,clazzName);
        return Result.ok(pageRs);
    }

    @ApiOperation("修改教师信息")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@ApiParam("教师的json格式信息") @RequestBody Teacher teacher){
        if(teacher.getPassword() != null && !teacher.getPassword().equals("") ){
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        }
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    @ApiOperation("删除教师信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@ApiParam("需要删除教师的id的json集合") @RequestBody List<Integer> ids){
        teacherService.removeByIds(ids);
        return Result.ok();
    }
}
