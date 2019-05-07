package com.example.demo.Entity;

import lombok.Data;

import java.util.List;

/**
 * @Author hx
 * @Date 2019/5/6 4:38 PM
 * @Version 1.0
 * @Description TODO
 */

@Data
public class XResponse {

    private int code;
    private String msg;
    private List<String> data;

}
