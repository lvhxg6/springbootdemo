package com.example.demo.exception;

/**
 * @Author: g6
 * @Date: 2019/4/30 15:04
 */
public class PostCallFailedException extends RuntimeException{

    public PostCallFailedException(String message){
        super(message);
    }

    public PostCallFailedException(String message, Throwable t){
        super(message,t);
    }

}
