package com.example.demo.vo.user;

import lombok.Data;

/**
 * @Author hx
 * @Date 2019/4/15 10:47 PM
 * @Version 1.0
 * @Description 查询用户请求参数封装
 */

@Data
public class QueryUserVo {

    public String name;
    public Integer age;
    public String gender;
    public String address;

}
