package com.example.demo.controller;

import com.example.demo.Entity.UserInfo;
import com.example.demo.Entity.YamlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huixiaolv on 17/05/2018.
 */
@RestController
@RequestMapping("/userinfo")
public class UserInforController {

    @Autowired
    UserInfo userInfo;

    @Autowired
    YamlEntity yml;

    @RequestMapping(method = RequestMethod.GET)
    public UserInfo userInfo(){
        return userInfo;
    }

    @RequestMapping(value = "/yml",method = RequestMethod.GET)
    public YamlEntity yml(){
        return yml;
    }

}
