package com.cmcc.syw.service.impl;

import com.cmcc.syw.dao.UserMapper;
import com.cmcc.syw.model.User;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sunyiwei on 16/4/21.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl();

    @Mock
    UserMapper userMapper;

    /**
     * 顺序校验,这个测试用例能通过,因为调用的顺序与校验的顺序一致
     */
    @Test
    public void testInorder1() throws Exception {
        when(userMapper.get(anyString())).thenReturn(new User());
        when(userMapper.getByName(anyString())).thenReturn(new User());

        assertNotNull(userService.get("fdfd"));
        assertNotNull(userService.getByName("fdfd"));

        InOrder inOrder = inOrder(userMapper);
        inOrder.verify(userMapper).get(anyString());
        inOrder.verify(userMapper).getByName(anyString());
    }

    /**
     * 顺序校验,这个测试用例过不了,因为调用的顺序和校验的顺序不一致
     */
    @Test
    @Ignore
    public void testInorder2() throws Exception {
        when(userMapper.get(anyString())).thenReturn(new User());
        when(userMapper.getByName(anyString())).thenReturn(new User());

        assertNotNull(userService.get("fdfd"));
        assertNotNull(userService.getByName("fdfd"));

        InOrder inOrder = inOrder(userMapper);
        inOrder.verify(userMapper).getByName(anyString());
        inOrder.verify(userMapper).get(anyString());
    }

    /**
     * 没有对调用的顺序进行校验
     */
    @Test
    public void testUninorder3() throws Exception {
        when(userMapper.get(anyString())).thenReturn(new User());
        when(userMapper.getByName(anyString())).thenReturn(new User());

        assertNotNull(userService.get("fdfd"));
        assertNotNull(userService.getByName("fdfd"));

        verify(userMapper).getByName(anyString());
        verify(userMapper).get(anyString());
    }
}

