package com.example.demo.service.impl;

import com.example.demo.exception.PostCallFailedException;
import com.example.demo.service.RetryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @Author: g6
 * @Date: 2019/4/30 14:31
 */
@Service("service")
public class RetryServiceImpl implements RetryService {

    private static Logger logger = LoggerFactory.getLogger(RetryServiceImpl.class);

    @Override
    @Retryable(value = Exception.class,maxAttempts = 3,backoff = @Backoff(delay = 1000L,multiplier = 2))
    public void query() {
        logger.debug("开始查询：......");
        throw new PostCallFailedException("123");
//        int i = 1 / 0;
//        logger.debug("执行结束：......");
    }


//    @Recover
//    public void recover(Exception e){
//        logger.debug("0000......");
//    }

    @Recover
    public void recover1(Exception e){
        logger.debug("1111......");
    }

    @Recover
    public void recover2(Exception e){
        logger.debug("2222......");
    }

}
