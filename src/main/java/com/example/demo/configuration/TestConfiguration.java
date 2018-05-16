package com.example.demo.configuration;

import com.example.demo.Entity.Properties;
import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huixiaolv on 15/05/2018.
 */
@Configuration
public class TestConfiguration {

    @Autowired
    Properties properties;

    @Bean
    public String message(){
        return "this is a message!";
    }

    @Bean
    public User user(){
        User user = new User();
        user.setName(properties.getName());
        user.setPassword(properties.getPwd());
        return user;
    }

}
