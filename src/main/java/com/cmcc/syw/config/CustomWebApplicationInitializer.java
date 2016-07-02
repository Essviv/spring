package com.cmcc.syw.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * Created by sunyiwei on 16/7/2.
 */
public class CustomWebApplicationInitializer extends AbstractDispatcherServletInitializer {
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        XmlWebApplicationContext cpxac = new XmlWebApplicationContext();
        cpxac.setConfigLocation("/WEB-INF/mvc-dispatcher-servlet.xml");

        return cpxac;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
