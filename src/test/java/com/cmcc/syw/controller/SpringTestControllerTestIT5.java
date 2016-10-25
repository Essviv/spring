package com.cmcc.syw.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 使用spring-test进行集成测试，增加事务管理
 * <p/>
 * Created by sunyiwei on 2016/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ContextConfiguration(locations = {"classpath:conf/applicationContext.xml",
    "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
public class SpringTestControllerTestIT5 {
    @Autowired
    SpringTestController springTestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(springTestController).build();
    }

    @Test
    public void testTest3() throws Exception {
        mockMvc.perform(get("/spring-test/test3")
            .param("count", new SimpleDateFormat("HHmmss").format(new Date()))
            .param("version", "0"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }
}