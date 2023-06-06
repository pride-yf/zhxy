package com.example.zhxy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yf
 * @date: 2023/3/30 - 12:47
 * @description:数据库tb_admin表对应的实体类
 */

@Data  //hashcode，tostring(),get/set
@AllArgsConstructor // 全参构造器
@NoArgsConstructor  // 无参构造器
@TableName("tb_admin")
public class Admin {
    @TableId(value = "id",type = IdType.AUTO)//主键标记
    private Integer id;
    private String name;
    private Character gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String portraitPath;//头像的图片路径。注意此处有驼峰式转换

}
