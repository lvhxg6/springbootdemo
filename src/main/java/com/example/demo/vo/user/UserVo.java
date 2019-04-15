package com.example.demo.vo.user;

import lombok.Data;

import java.util.List;

/**
 * @Author: g6
 * @Date: 2019/4/15 11:22
 */
@Data
public class UserVo {

    String userName;
    Integer gender;
    String address;
    List<Hobby> hobbies;

}
