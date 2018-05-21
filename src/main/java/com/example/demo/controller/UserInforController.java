package com.example.demo.controller;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Entity.UserInfo;
import com.example.demo.Entity.YamlEntity;
import com.example.demo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
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

    /**
     * http://192.168.10.73:8088/user/5?username=祖超&age=23&address=北京市故宫博物院
     * @param uid
     * @param name
     * @param age
     * @param address
     * @return
     */
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
     * 这样也可以  http://192.168.10.73:8088/user/5?username=祖超&age=24&address=北京市石景山特区
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


    @RequestMapping(value="/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            logger.info("文件的后缀名为：" + suffixName);

            // 设置文件存储路径
            String filePath = "//demo//data//";
            String path = filePath + fileName;

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            logger.debug("uoload1 success...........");
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("download2 failed...........");
        return "上传失败";
    }

    //多文件上传
    @RequestMapping(value = "/uploadMore", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "//demo//data//";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败  ==> "
                            + e.getMessage();
                }
            } else {
                return "第 " + i
                        + " 个文件上传失败因为文件为空";
            }
        }
        logger.debug("upload2 success...........");
        return "上传成功";
    }

    //文件下载相关代码
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "data.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = "//demo//data";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                    logger.debug("download success...........");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
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
