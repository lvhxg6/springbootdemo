package com.example.demo.service;

import com.example.demo.http.XResponse;

/**
 * @Author: g6
 * @Date: 2019/4/30 14:45
 */
public interface HttpCallService{

    XResponse getCall(String req);

    XResponse postCall(String req);

}
