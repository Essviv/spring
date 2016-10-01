package com.cmcc.syw.webservice;

import org.apache.cxf.feature.Features;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by sunyiwei on 16/10/1.
 */
@WebService
@Features(features = {"org.apache.cxf.feature.LoggingFeature"})
public class Server {
    @WebMethod
    public String sayHello(String name) {
        return "Hi, " + name;
    }
}
