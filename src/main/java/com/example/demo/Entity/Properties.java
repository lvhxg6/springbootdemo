package com.example.demo.Entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by huixiaolv on 14/05/2018.
 */
@Component
public class Properties {

    @Value("${login.name}")
    private String name;
    @Value("${user.password}")
    private String pwd;

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }
}
