package com.example.demo.service.impl;

import com.example.demo.exception.GetCallFailedException;
import com.example.demo.exception.PostCallFailedException;
import com.example.demo.http.XHttpClient;
import com.example.demo.http.XResponse;
import com.example.demo.service.HttpCallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @Author: g6
 * @Date: 2019/4/30 14:47
 */
@Service
public class HttpCallServiceImpl implements HttpCallService{

    private static Logger logger = LoggerFactory.getLogger(HttpCallServiceImpl.class);

    @Autowired
    private XHttpClient httpClient;

    @Override
    @Retryable(value = GetCallFailedException.class,maxAttempts = 3,
            backoff = @Backoff(delay = 1000L,multiplier = 2))
    public XResponse getCall(String req) {
        XResponse get = httpClient.get(req, XResponse.class);
        if(get.getCode()!=0){
            throw new GetCallFailedException(get.getMsg());
        }
        return get;
    }

    @Recover
    public void getRecover(GetCallFailedException e){
        logger.debug("get recover......");
    }

    @Override
    @Retryable(value = PostCallFailedException.class , maxAttempts = 3, backoff = @Backoff(delay = 1000L,multiplier = 1))
    public XResponse postCall(String req) {
        XResponse post = httpClient.post(req, XResponse.class);
        if(post.getCode()!=0){
            throw new PostCallFailedException(post.getMsg());
        }
        System.out.println(">>>>>>>>>>success<<<<<<<<<<");
        return post;
    }

    @Recover
    public XResponse postRecover(PostCallFailedException e){
        System.out.println("post recover......");
        return null;
    }

}
