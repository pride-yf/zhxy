package com.example.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.zhxy.pojo.Clazz;
import com.example.zhxy.pojo.Student;

import java.util.List;

/**
 * @author: yf
 * @date: 2023/3/30 - 15:21
 * @description:
 */
public interface ClazzService extends IService<Clazz> {
    IPage<Clazz> getClazzs(Page<Clazz> pageParam,Clazz clazz);

    List<Clazz> getClazzs();
}
