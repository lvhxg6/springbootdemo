package com.example.demo.aop.paramcopy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: g6
 * @Date: 2019/4/15 17:04
 * @Destribution 全局业务bean容器
 */
public class GlobalBussessBeanContainer {

    private static ConcurrentHashMap<String,Object> beanContainer = new ConcurrentHashMap<>();


    public static void putBean2Container(String key,Object o){

            beanContainer.put(warrper(key),o);
    }

    public static Object getBean(String key){
        if(beanContainer.containsKey(warrper(key))){
            return beanContainer.remove(warrper(key));
        }
        return null;
    }

    private static String warrper(String key){
        String name = Thread.currentThread().getName();
        return new StringBuilder().append(name).append("-").append(key).toString();
    }


}
