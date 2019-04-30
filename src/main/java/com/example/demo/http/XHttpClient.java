package com.example.demo.http;

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

/**
 * @Author: g6
 * @Date: 2019/4/30 15:23
 */
@Component
public class XHttpClient {

    private static Logger logger = LoggerFactory.getLogger(HttpCallServiceImpl.class);

    @Value("${test.g6.url}")
    private String url;

    public String sendPost(String param) throws PostCallFailedException{
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
            out.print(param);
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

    public <T> T get(String o,Class<T> clazz){
        return null;
    }

    public <T> T post(String req,Class<T> clazz){
        String s = sendPost(req);
        if(clazz!=null){
            try{
                return JsonUtils.getJsonToBean(s, clazz);
            }catch (Exception e){
                e.printStackTrace();
                throw new PostCallFailedException(e.getMessage());
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
