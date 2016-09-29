package com.cmcc.syw.cxf;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * Created by sunyiwei on 16/9/28.
 */
public class Client {
    public static void main(String[] args) {
        final String ADDR = "http://localhost:9999/helloWorld?wsdl";

        URL url = buildUrl(ADDR);
        Service service = Service.create(url, new QName("http://cxf.syw.cmcc.com/", "HelloWorld"));

        HelloWorld helloWorld = service.getPort(HelloWorld.class);
        System.out.println(helloWorld.sayHi("孙义威"));
    }

    private static URL buildUrl(String address) {
        URL url = null;

        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
