package com.example.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.zhxy.pojo.Student;
import com.example.zhxy.service.StudentService;
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
@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //delStudentById
    @ApiOperation("删除或者批量删除学生信息")
    @DeleteMapping("/delStudentById")
    public Result deleteStudentById(@ApiParam("学生的id集合")@RequestBody List<Integer> ids){
        studentService.removeByIds(ids);
        return  Result.ok();
    }


    //addOrUpdateStudent
    @ApiOperation("增加或者修改学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@ApiParam("将json格式输入解析成一个Student对象") @RequestBody
                                     Student student){
        if(student.getPassword() != null && !student.getPassword().equals("")){
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }


    @ApiOperation("根据班级名或者学生姓名信息进行分页查询")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@ApiParam("页码数") @PathVariable("pageNo") Integer pageNo,
                                  @ApiParam("页码大小") @PathVariable("pageSize") Integer pageSize,
                                  @ApiParam("学生姓名")  String name,
                                  @ApiParam("班级名") String clazzName
                                  ){
        Page<Student> page = new Page<Student>(pageNo,pageSize);

        IPage<Student> pageRs = studentService.getStudentByOpr(page,clazzName,name);

        return Result.ok(pageRs);
    }
}
