package com.example.demo.controller;

import com.example.demo.Entity.Properties;
import com.example.demo.Entity.User;
import com.example.demo.service.UserInfoService;
import com.example.demo.service.UserService;
import com.example.demo.vo.user.QueryUserVo;
import com.example.demo.vo.user.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by huixiaolv on 14/05/2018.
 * 主要体验了一下restful API 相应注解的使用
 */
@RestController
@RequestMapping("/users")
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    Properties properties;

    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/index")
    public String index(){
        logger.debug("/index");
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> user(){
        logger.debug("/users");
        return userService.users();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable("id")String id){
        logger.debug("/users/"+id+" method:get......");
        return userService.queryUser(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public List<User> addUser(@PathVariable("id")String uid,@RequestParam("name")String name,@PathParam("password")String password){
        logger.debug("/users/"+uid+" method:post...... uid:"+uid+" name:"+name+" password:"+password);
        User user = new User();
        user.setUid(uid);
        user.setName(name);
        user.setPassword(password);
        userService.addUser(user);
        return userService.users();
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
    public List<User> addUsers(@RequestBody User user){
        userService.addUser(user);
        return userService.users();
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
     * @param password
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public List<User> updateUser(@PathVariable("id")String uid, @RequestParam(name="uname",required = false)String name, @RequestParam("password")String password){
        logger.debug("/users/"+uid+" method:put.....uid:"+uid+" name:"+name+" password:"+password);
        User user = new User();
        user.setUid(uid);
        user.setName(name);
        user.setPassword(password);
        userService.updateUser(user);
        return userService.users();
    }

    /**
     * 注解@PathVariable 是从url中取值，URL类似：http://localhost:8080/users/path/1/111/11111
     * @param uid
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "/path/{id}/{name}/{password}",method = RequestMethod.PUT)
    public List<User> updateUser1(@PathVariable("id")String uid,@PathParam("name") String name,@PathParam("password")String password){
        logger.debug("/users/"+uid+" method:put.....uid:"+uid+" name:"+name+" password:"+password);
        User user = new User();
        user.setUid(uid);
        user.setName(name);
        user.setPassword(password);
        userService.updateUser(user);
        return userService.users();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public List<User> deleteUser(@PathVariable("id")String id){
        logger.debug("/users/"+id+" method:delete......id:"+id);
        userService.deleteUser(id);
        return userService.users();
    }


    @RequestMapping(value = "/patch",method = RequestMethod.POST)
    public String patchUser(@RequestBody UserVo userVo){
        userInfoService.patchUser(userVo);
        return "success";
    }

    @RequestMapping(value = "/xquery",method = RequestMethod.POST)
    public String xquery(@RequestBody QueryUserVo userVo){
        userInfoService.queryUser(userVo);
        return "success";
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
