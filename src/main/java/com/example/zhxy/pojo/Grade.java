package com.example.zhxy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yf
 * @date: 2023/3/30 - 13:05
 * @description:数据库tb_grade表对应的实体类
 * 未生成联合主键
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_grade")
public class Grade {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableId(value = "name",type = IdType.AUTO)
    private String name;
    private String manager;
    private String email;
    private String telephone;
    private String introducation;
}
