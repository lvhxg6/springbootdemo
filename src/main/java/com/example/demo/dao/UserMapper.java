package com.example.demo.dao;

import com.example.demo.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by huixiaolv on 18/05/2018.
 */
@Mapper
public interface UserMapper {
    public int addUser(UserEntity user);
    public int updateUser(UserEntity user);
    public int deleteUser(String id);
    public UserEntity queryUserByID(String id);
    public List<UserEntity> queryUsers();
}
