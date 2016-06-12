package com.cmcc.syw.service.impl;

import com.cmcc.syw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by sunyiwei on 16/4/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void testInsert() throws Exception {
        System.out.println(userService.insert());
    }
}