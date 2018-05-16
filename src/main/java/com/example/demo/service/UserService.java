package com.example.demo.service;

import com.example.demo.Entity.User;

import java.util.List;

/**
 * Created by huixiaolv on 16/05/2018.
 */
public interface UserService {
    public void addUser(User user);
    public void updateUser(User user);
    public List<User> users();
    public void deleteUser(String uid);
    public User queryUser(String id);
}
