package com.example.zhxy.pojo;

import lombok.Data;

/**
 * @author: yf
 * @date: 2023/3/30 - 15:08
 * @description:用户登录表单信息
 */
@Data
public class LoginForm {
    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}
