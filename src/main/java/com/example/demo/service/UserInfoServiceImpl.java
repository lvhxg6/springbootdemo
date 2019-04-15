package com.example.demo.service;

import com.example.demo.Entity.UserEntity;
import com.example.demo.annotation.XCopy;
import com.example.demo.dao.UserMapper;
import com.example.demo.dto.user.UserDto;
import com.example.demo.vo.user.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by huixiaolv on 18/05/2018.
 */
//@ComponentScan({"com.example.demo.dao"}) 注释了这个也好使
@Service
public class UserInfoServiceImpl implements UserInfoService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;


    @Override
    public void addUser(UserEntity user) {
        try{
            logger.debug("service--addUser--"+user);
            userMapper.addUser(user);
        }catch (Exception e){
            logger.debug("addUser Exception:",e);
        }
    }

    @Override
    public void updateUser(UserEntity user) {
        try{
            logger.debug("service--updateUser--"+user);
            userMapper.updateUser(user);
        }catch (Exception e){
            logger.debug("updateUser Exception:",e);
        }
    }

    @Override
    public List<UserEntity> users() {
        List<UserEntity> users = null;
        try{
            users = userMapper.queryUsers();
            logger.debug("service--users--size--"+users.size());
            if(users.size()>0)
                logger.debug("service--users--"+users.get(0));
        }catch (Exception e){
            logger.debug("users Exception:",e);
        }
        return users;
    }

    @Override
    public void deleteUser(String uid) {
        try{
            logger.debug("service--deleteUser--uid:"+uid);
            userMapper.deleteUser(uid);
        }catch (Exception e){
            logger.debug("deleteUser Exception:",e);
        }
    }

    @Override
    public UserEntity queryUser(String id) {
        UserEntity user = null;
        try{
            user = userMapper.queryUserByID(id);
            logger.debug("service--queryUser--uid:"+id+" UserEntity:"+((user==null)?"null":user));
        }catch (Exception e){
            logger.debug("queryUser Exception:",e);
        }
        return user;
    }

    @XCopy
    @Override
    public void patchUser(UserVo uvo, UserDto.request udto) {
        logger.debug(udto.toString());
    }
}
