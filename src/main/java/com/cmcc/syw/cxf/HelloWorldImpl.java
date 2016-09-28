package com.cmcc.syw.cxf;

import javax.jws.WebService;

/**
 * Created by sunyiwei on 2016/9/28.
 */
@WebService(serviceName = "HelloWorld", endpointInterface = "com.cmcc.syw.cxf.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String sayHi(String name) {
        return "Hi, " + name;
    }
}
