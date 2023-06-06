package com.example.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.zhxy.pojo.Grade;
import com.example.zhxy.service.GradeService;
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
@Api(tags="年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @ApiOperation("查询全部年级，用于班级查询时选定年级")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades = gradeService.getGrades();
        return Result.ok(grades);
    }

    @ApiOperation("根据年级名称模糊查询，带分页")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(@ApiParam("分页查询的页码数")@PathVariable("pageNo") Integer pageNo,
                            @ApiParam("分页查询的页码大小")@PathVariable("pageSize") Integer pageSize,
                            @ApiParam("分页查询的名称模糊匹配")String gradeName){
        //分页带条件查询
        Page<Grade> page = new Page<Grade>(pageNo,pageSize);
        //通过服务层(需要组合GradeService)
        IPage<Grade> pageRs =  gradeService.getGradeByOpr(page,gradeName);

        //封装Result对象并返回

        return Result.ok(pageRs);
    }

    @ApiOperation("保存Grade信息修改或新增，有id属性是修改，没有是增加")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@ApiParam("Json格式的Grade对象")@RequestBody Grade grade){
        //接收参数，调用服务层方法，完成增加或者是修改
        gradeService.saveOrUpdate(grade);

        return Result.ok();
    }

    @ApiOperation("删除或者批量删除Grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@ApiParam("要删除的所有Grade的id的json集合") @RequestBody List<Integer> ids){
        gradeService.removeByIds(ids);
        return Result.ok();
    }
}
