package com.cmcc.syw.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sunyiwei on 2016/10/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
public class JQueryControllerTest {
    MockMvc mockMvc = null;
    @InjectMocks
    private JQueryController jQueryController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jQueryController).build();
    }

    @Test
    public void testTest3() throws Exception {
        mockMvc.perform(get("/jquery/test3")
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
}