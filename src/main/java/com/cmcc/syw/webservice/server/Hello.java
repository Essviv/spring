package com.cmcc.syw.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by sunyiwei on 16/9/26.
 */
@WebService
public class Hello {
    public static void main(String[] args) {
        Hello hello = new Hello();
        Endpoint endpoint = Endpoint.publish("http://localhost:12345/hello", hello);
    }

    @WebMethod
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
