package com.cmcc.syw.controller;

import com.cmcc.syw.model.Account;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 使用standaloneSetup配置进行集成测试
 * <p/>
 * Created by sunyiwei on 2016/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:conf/applicationContext.xml",
    "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
public class SpringTestControllerTestIT2 {
    @Autowired
    SpringTestController springTestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(springTestController).build();
    }

    @Test
    public void testTest1() throws Exception {
        String content = "helloWorld";
        mockMvc.perform(get("/spring-test/test1").param("name", content))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(build(content)));
    }

    @Test
    public void testTest2() throws Exception {
        String content = "helloWorld";
        mockMvc.perform(get("/spring-test/test2").param("name", content))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(build(content)));
    }

    @Test
    public void testTest3() throws Exception {
        mockMvc.perform(get("/spring-test/test3")
            .param("count", new SimpleDateFormat("hhmmss").format(new Date()))
            .param("version", "0"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }

    private String build(String value) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", value);

        return new Gson().toJson(map);
    }

    private Account build(int count) {
        Account account = new Account();
        account.setCount(count);
        account.setVersion(0);

        return account;
    }
}