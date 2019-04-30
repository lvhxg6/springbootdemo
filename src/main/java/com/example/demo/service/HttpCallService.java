package com.example.demo.service;

import com.example.demo.http.XResponse;

/**
 * @Author: g6
 * @Date: 2019/4/30 14:45
 */
public interface HttpCallService{

    <T> T getCall(String req,Class<T> clazz);

    XResponse postCall(String req);

}
