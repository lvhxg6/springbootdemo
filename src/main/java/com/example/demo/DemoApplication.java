package com.example.demo;

import com.example.demo.http.XResponse;
import com.example.demo.service.RetryService;
import com.example.demo.service.impl.HttpCallServiceImpl;
import com.example.demo.utils.JsonUtils;
import com.example.demo.vo.user.QueryUserVo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

//
@EnableRetry(proxyTargetClass=true)
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);

//		RetryService service = run.getBean(RetryService.class);
//		service.query();
//
//		HttpCallServiceImpl service1 = run.getBean(HttpCallServiceImpl.class);
//
//		try {
//			QueryUserVo vo = new QueryUserVo();
//			vo.setAddress("address");
//			vo.setAge(1);
//			vo.setGender("f");
//			vo.setName("hx");
//			XResponse xResponse = service1.postCall(JsonUtils.getBeanToJson(vo));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
