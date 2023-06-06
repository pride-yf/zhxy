package com.example.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zhxy.mapper.ClazzMapper;
import com.example.zhxy.pojo.Clazz;
import com.example.zhxy.pojo.Student;
import com.example.zhxy.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author: yf
 * @date: 2023/3/30 - 15:24
 * @description:
 */
@Service("clazzServiceImpl")
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {
    @Override
    public IPage<Clazz> getClazzs(Page<Clazz> pageParam, Clazz clazz) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
        if(clazz != null){
            String gradeName = clazz.getGradeName();
            if(!StringUtils.isEmpty(gradeName)){
                queryWrapper.like("grade_name",gradeName);
            }
            String name = clazz.getName();
            if(!StringUtils.isEmpty(name)){
                queryWrapper.like("name",name);
            }
        }

        Page<Clazz> gradePage = baseMapper.selectPage(pageParam, queryWrapper);
        return gradePage;

    }

    @Override
    public List<Clazz> getClazzs() {

        return baseMapper.selectList(null);
    }
}
