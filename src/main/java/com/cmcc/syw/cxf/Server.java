package com.cmcc.syw.cxf;

import javax.xml.ws.Endpoint;

/**
 * Created by sunyiwei on 2016/9/28.
 */
public class Server {
    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorldImpl();
        String address = "http://localhost:9999/helloWorld";

        Endpoint.publish(address, helloWorld);
    }
}
