package com.example.demo.controllertest;

import com.example.demo.controller.IndexController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by huixiaolv on 14/05/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@ComponentScan
@WebAppConfiguration
public class SampleControllerTest {

    private MockMvc mock;

    @Before
    public void setUp() throws Exception{
        mock = MockMvcBuilders.standaloneSetup(new IndexController()).build();
    }

    @Test
    public void testGetBeanMessage() throws Exception{
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.scan("com.example.demo");
//        ctx.refresh();
//        assertEquals("this is a message!",ctx.getBean("message"));
    }

    @Test
    public void testIndex() throws Exception{
//        mock.perform(MockMvcRequestBuilders.get("/index").accept(MediaType.ALL))
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("index")));
    }

}
