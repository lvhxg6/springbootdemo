package com.example.demo.http;

import com.example.demo.exception.GetCallFailedException;
import com.example.demo.exception.PostCallFailedException;
import com.example.demo.service.impl.HttpCallServiceImpl;
import com.example.demo.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @Author: g6
 * @Date: 2019/4/30 15:23
 */
@Component
public class XHttpClient {

    private static Logger logger = LoggerFactory.getLogger(HttpCallServiceImpl.class);

    @Value("${test.g6.url}")
    private String url;

    private String sendPost(String jsonParam) throws PostCallFailedException{
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        logger.debug("url:"+url);

        try {
            if(null==url||"".equals(url)){
                return null;
            }

            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setRequestProperty("Content-type","application/json; charset=utf-8");
            conn.setConnectTimeout(10*1000);
            conn.setReadTimeout(10*1000);

            conn.setDoOutput(true);
            conn.setDoInput(true);


            out = new PrintWriter(conn.getOutputStream());
            out.print(jsonParam);
            out.flush();
            out.close();

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            result = getInnerErrorMsg(e.getMessage());
            System.out.println("发送 POST 请求出现异常！"+e);
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    private String sendGet(String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            result = getInnerErrorMsg(e.getMessage());
            System.out.println("发送 Get 请求出现异常！"+e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public <T> T get(String param,Class<T> clazz){
        String s = sendGet(param);
        if(clazz!=null){
            try{
                return JsonUtils.getJsonToBean(s,clazz);
            }catch (Exception e){
                logger.error("get请求响应参数转换异常：",e);
            }
        }
        return null;
    }

    public <T> T post(String req,Class<T> clazz){
        String s = sendPost(req);
        if(clazz!=null){
            try{
                return JsonUtils.getJsonToBean(s, clazz);
            }catch (Exception e){
                logger.error("post请求响应参数转换异常：",e);
            }
        }
        return null;
    }

    private XResponse getInnerError(String msg){
        XResponse response = new XResponse();
        response.setCode(-1);
        response.setMsg(msg);
        return response;
    }
    private String getInnerErrorMsg(String msg){
        return JsonUtils.getBeanToJson(getInnerError(msg));
    }

}
