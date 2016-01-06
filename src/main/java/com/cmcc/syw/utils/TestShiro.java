package com.cmcc.syw.utils;

import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sunyiwei on 15-12-25.
 */
@Component
public class TestShiro {
    @Autowired
    SecurityManager securityManager;

    public void test(){

    }
}
