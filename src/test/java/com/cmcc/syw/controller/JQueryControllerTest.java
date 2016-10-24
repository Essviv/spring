package com.cmcc.syw.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
<<<<<<< 7f43d155a44ba27e5be73c2d43ab9efbc1e05f02
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sunyiwei on 2016/10/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:conf/applicationContext.xml",
    "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
public class JQueryControllerTest {
    MockMvc mockMvc = null;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testTest3() throws Exception {
        String content = "hello_world";
        mockMvc.perform(get("/jquery/test3")
            .param("test", content))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(content));
                .param("test", "hello_world")
                .header("Content-Type", "application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("hello_world")));
    }

    @Test
    public void testTest1() throws Exception {
        mockMvc.perform(post("/jquery/test"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testTest4() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("get");
        request.setRequestURI("/jquery/test4");
        request.setParameter("name", "sunyiwei");
        request.setParameter("value", "handsome-guy");

        MockHttpServletResponse response = new MockHttpServletResponse();

        jQueryController.test4(request, response);

        String output = "";

        System.out.println(output);

        assertNotNull(output);
    }
}
