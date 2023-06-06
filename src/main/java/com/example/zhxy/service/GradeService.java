package com.example.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.zhxy.pojo.Grade;
import com.example.zhxy.util.Result;

import java.util.List;

/**
 * @author: yf
 * @date: 2023/3/30 - 15:22
 * @description:
 */
public interface GradeService extends IService<Grade> {

    public IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName);

    public List<Grade> getGrades();
}
