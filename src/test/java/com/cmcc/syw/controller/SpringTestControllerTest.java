package com.cmcc.syw.controller;

import com.cmcc.syw.model.Account;
import com.cmcc.syw.service.AccountService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * plain ut test
 * <p/>
 * Created by sunyiwei on 2016/10/20.
 */
@RunWith(MockitoJUnitRunner.class)
public class SpringTestControllerTest {
    @InjectMocks
    SpringTestController springTestController;

    @Mock
    AccountService accountService;

    @Test
    public void testTest1() throws Exception {
        String content = "helloWorld";
        String result = springTestController.test1(content);
        assertEquals(result, build(content));
    }

    @Test
    public void testTest2() throws Exception {
        String content = "sunywiei";
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(printWriter);

        springTestController.test2(content, response);
        assertEquals(stringWriter.toString(), build(content));

        verify(response).getWriter();
    }

    private String build(String value) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", value);

        return new Gson().toJson(map);
    }

    @Test
    public void testTest3() throws Exception {
        when(accountService.insert(any(Account.class)))
            .thenReturn(false).thenReturn(true);

        assertFalse(springTestController.test3(build(10)));
        assertTrue(springTestController.test3(build(10)));
    }

    private Account build(int count) {
        Account account = new Account();
        account.setCount(count);
        account.setVersion(0);

        return account;
    }
}