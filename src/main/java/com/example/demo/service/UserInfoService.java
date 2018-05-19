package com.example.demo.service;

import com.example.demo.Entity.UserEntity;

import java.util.List;

/**
 * Created by huixiaolv on 18/05/2018.
 */
public interface UserInfoService {
    public void addUser(UserEntity user);
    public void updateUser(UserEntity user);
    public List<UserEntity> users();
    public void deleteUser(String uid);
    public UserEntity queryUser(String id);
}
