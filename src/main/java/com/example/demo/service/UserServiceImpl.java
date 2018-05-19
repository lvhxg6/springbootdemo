package com.example.demo.service;

import com.example.demo.Entity.User;
import com.example.demo.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huixiaolv on 16/05/2018.
 */

@Service
public class UserServiceImpl implements UserService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static List<User> users = new ArrayList<User>();

    static{
        User user = new User();
        user.setUid("1");
        user.setName("hx");
        user.setPassword("123456");
        users.add(user);
    }

    @Autowired
    private UserMapper userMapper;


    @Override
    public void addUser(User user) {
        try{
            users.add(user);
        }catch (Exception e){
            logger.error("addUser",e);
        }
    }

    @Override
    public void updateUser(User user) {
        try{
            users.forEach(user1 -> {
                if(user1.getUid().equalsIgnoreCase(user.getUid())){
                    user1.setName(user.getName());
                    user1.setPassword(user.getPassword());
                }
            });
        }catch(Exception e){
            logger.error("updateUser",e);
        }
    }

    @Override
    public List<User> users() {
        return users;
    }

    @Override
    public void deleteUser(String uid) {
        try{
            Iterator<User> iterator = users.iterator();
            while(iterator.hasNext()){
                User next = iterator.next();
                iterator.remove();
                if(next.getUid().equalsIgnoreCase(uid)){
                    users.remove(next);
                    break;
                }
            }

        }catch(Exception e){
            logger.error("deleteUser",e);
        }
    }

    @Override
    public User queryUser(String id) {
        final User returnUser = new User();
        try{
            users.forEach(user1 -> {
                if(user1.getUid().equalsIgnoreCase(id)){
                    returnUser.setUid(user1.getUid());
                    returnUser.setName(user1.getName());
                    returnUser.setPassword(user1.getPassword());
                }
            });
        }catch(Exception e){
            logger.error("queryUser",e);
        }
        return returnUser;
    }
}
