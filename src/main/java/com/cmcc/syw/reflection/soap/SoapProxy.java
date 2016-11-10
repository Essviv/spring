package com.cmcc.syw.reflection.soap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by sunyiwei on 2016/11/10.
 */
public class SoapProxy implements InvocationHandler {
    private String url;
    private String serviceName;
    private String encoding;

    public SoapProxy(String url, String serviceName, String encoding) {
        this.url = url;
        this.serviceName = serviceName;
        this.encoding = encoding;
    }

    public static Object createProxy(String url, String serviceName, String encoding) {
        return Proxy.newProxyInstance(SoapProxy.class.getClassLoader(),
                new Class[]{WeatherService.class}, new SoapProxy(url, serviceName, encoding));
    }

    public static void main(String[] args) {
        final String url = "";
        final String serviceName = "";
        final String encoding = "";
        final String cityName = "";

        WeatherService weatherServiceProxy =
                (WeatherService) SoapProxy.createProxy(url, serviceName, encoding);
        System.out.println(weatherServiceProxy.query(cityName));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("模拟发送SOAP请求...");

        //模拟SOAP返回
        Object obj = "HOT";
        return obj;
    }
}
