package com.example.demo.exception;

/**
 * @Author: g6
 * @Date: 2019/4/30 14:49
 */
public class GetCallFailedException extends RuntimeException{

    public GetCallFailedException(String message){
        super(message);
    }

    public GetCallFailedException(String message, Throwable t){
        super(message,t);
    }

}
