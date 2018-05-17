package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by huixiaolv on 17/05/2018.
 * 注解@ControllerAdvice全局处理异常，包含@Component，都可以被扫描到
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler({Exception.class})
    public ModelAndView formatErrorHandler(HttpServletRequest request,Exception e) throws Exception{
        logger.error("GlobalDefaultExceptionHandler:formatErrorHandler",e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("error","参数类型错误");
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.addObject("timestamp", new Date());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}
