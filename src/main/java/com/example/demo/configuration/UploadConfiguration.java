package com.example.demo.configuration;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by huixiaolv on 21/05/2018.
 * 文件上传限制文件大小的配置类
 *
 */
@Configuration
public class UploadConfiguration {
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        multipartConfigFactory.setMaxFileSize("100MB");
        multipartConfigFactory.setMaxRequestSize("100MB");
        return multipartConfigFactory.createMultipartConfig();
    }
}
