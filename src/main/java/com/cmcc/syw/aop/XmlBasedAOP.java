package com.cmcc.syw.aop;

import org.apache.log4j.Logger;

/**
 * Created by sunyiwei on 2015/10/19.
 */
public class XmlBasedAOP {
    private  static final Logger LOGGER = Logger.getLogger(XmlBasedAOP.class);

    public void before(){
        LOGGER.error("This is output from customAOP.before()...");
    }

    public void after(){
        LOGGER.error("This is output from customAOP.after()...");
    }
}
