package com.cmcc.syw.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sunyiwei on 16/7/4.
 */
public class ApplicationContextTest {
    @Test
    public void testApplicationContext() throws Exception {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("conf/applicationContext.xml");

        BeanFactory beanFactory = applicationContext.getParentBeanFactory();

    }
}
