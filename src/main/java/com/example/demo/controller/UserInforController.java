package com.example.demo.controller;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Entity.YamlEntity;
import com.example.demo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by huixiaolv on 17/05/2018.
 */
@RestController
@RequestMapping("/user")
public class UserInforController {

    static Logger logger = LoggerFactory.getLogger(UserInforController.class);

    @Autowired
    UserInfo userInfo;

    @Autowired
    YamlEntity yml;

    @Autowired
    UserInfoService userInfoService;

//    @RequestMapping(method = RequestMethod.GET)
//    public UserInfo userInfo(){
//        return userInfo;
//    }

    @RequestMapping(value = "/yml",method = RequestMethod.GET)
    public YamlEntity yml(){
        return yml;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<UserEntity> user(){
        logger.debug("/users");
        return userInfoService.users();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public UserEntity getUser(@PathVariable("id")String id){
        logger.debug("/users/"+id+" method:get......");
        return userInfoService.queryUser(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public List<UserEntity> addUser(@PathVariable("id")String uid,
                              @RequestParam("username")String name,
                              @PathParam("age")String age,
                              @PathParam("address")String address){
        logger.debug("/users/"+uid+" method:post...... uid:"+uid+" name:"+name+" age:"+age+" address:"+address);
        UserEntity user = new UserEntity();
        user.setId(uid);
        user.setUserName(name);
        user.setAge(age);
        user.setAddress(address);
        userInfoService.addUser(user);
        return userInfoService.users();
    }


    /**
     * 注解RequestBody 前端对应的headers -> content-type:json/application  request Body中应该是json格式
     * 例如：
     * headers:   content-type:application/json
     * body:      { "uid": "3", "name": "3333", "password": "3333333"  }
     * springmvc 会把request body中的json发序列化到对应的对象中去
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<UserEntity> addUsers(@RequestBody UserEntity user){
        userInfoService.addUser(user);
        return userInfoService.users();
    }


    @RequestMapping(value = "/map",method = RequestMethod.POST)
    public String addUser(@RequestBody Map<String,String> map){
        Set<String> strings = map.keySet();
        for(String str:strings){
            logger.debug(str+" "+map.get(str));
        }
        return "ok";
    }

    /**
     * 注解RequestParam 前端对应的url应该为  http://localhost:8080/users/1?uname=qwe&password=123
     * @param uid
     * @param name
     * @param age
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public List<UserEntity> updateUser(@PathVariable("id")String uid,
                                 @RequestParam(name="username",required = false)String name,
                                 @RequestParam("age")String age,
                                 @RequestParam("address")String address){
        logger.debug("/users/"+uid+" method:put.....uid:"+uid+" name:"+name+" age:"+age+" address:"+address);
        UserEntity user = new UserEntity();
        user.setAddress(address);
        user.setAge(age);
        user.setId(uid);
        user.setUserName(name);
        userInfoService.updateUser(user);
        return userInfoService.users();
    }

    /**
     * 注解@PathVariable 是从url中取值，URL类似：http://localhost:8080/users/path/1/111/11111
     * @param uid
     * @param name
     * @param age
     * @return
     */
    @RequestMapping(value = "/path/{id}/{name}/{age}/{address}",method = RequestMethod.PUT)
    public List<UserEntity> updateUser1(@PathVariable("id")String uid,
                                  @PathParam("name") String name,
                                  @PathParam("age")String age,
                                  @PathParam("address")String address){
        logger.debug("/users/"+uid+" method:put.....uid:"+uid+" name:"+name+" age:"+age+" address:"+address);
        UserEntity user = new UserEntity();
        user.setUserName(name);
        user.setAge(age);
        user.setAddress(address);
        user.setId(uid);
        userInfoService.updateUser(user);
        return userInfoService.users();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public List<UserEntity> deleteUser(@PathVariable("id")String id){
        logger.debug("/users/"+id+" method:delete......id:"+id);
        userInfoService.deleteUser(id);
        return userInfoService.users();
    }


    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }



}
