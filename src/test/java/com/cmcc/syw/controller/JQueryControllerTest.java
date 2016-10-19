package com.cmcc.syw.controller;

import org.apache.commons.codec.Charsets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StreamUtils;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sunyiwei on 2016/10/19.
 */
@RunWith(MockitoJUnitRunner.class)
public class JQueryControllerTest {
    MockMvc mockMvc = null;

    @InjectMocks
    private JQueryController jQueryController = new JQueryController();

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

    @Test
    public void testTest4() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("get");
        request.setRequestURI("/jquery/test4");

        MockHttpServletResponse response = new MockHttpServletResponse();

        jQueryController.test4(request, response);

        String output = "";
        StreamUtils.copy(output, Charsets.UTF_8, response.getOutputStream());

        assertNotNull(output);
    }
}