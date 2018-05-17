package com.example.demo;

import com.example.demo.Entity.Properties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(locations="classpath:application.properties")
//@TestPropertySource(locations={"classpath:application.properties"})
public class DemoApplicationTests {

	/**
	 * 为什么启动时候配置文件中的配置属性无法赋值给bean中属性
	 * @throws Exception
     */
	@Autowired
	Properties properties;

	@Test
	public void testGetBeanMessage() throws Exception{
		/**
		 * ctx.scan/refresh方法之后之前springboot启动初始化的bean被重新覆盖了？为什么？
		 */
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.scan("com.example.demo.configuration");
//		ctx.refresh();
////		User user = (User)ctx.getBean("user");
//		System.out.println("userName:"+properties.getName()+" pwd:"+properties.getPwd());
////		assertEquals("this is a message!",ctx.getBean("message"));
	}

	@Test
	public void contextLoads() {
	}

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
//		mvc = MockMvcBuilders.standaloneSetup(new DemoApplicationTests()).build();
	}

	@Test
	public void getHello() throws Exception {
//		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo("Hello World")));
	}

}
