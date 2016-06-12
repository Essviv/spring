package com.cmcc.syw.model;

import com.thoughtworks.xstream.XStream;

/**
 * Created by sunyiwei on 2015/12/13.
 */
public class Main {
    public static void main(String[] args) {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);

        testOrderReq(xStream);
        testQueryReq(xStream);
    }

    private static void testOrderReq(XStream xStream){
        OrderReq orderReq = new OrderReq();
        orderReq.setName("Sunyiwei");
        orderReq.setAge(27);

        String xmlStr = xStream.toXML(orderReq);
        System.out.println(xmlStr);

        OrderReq orderReqBack = (OrderReq) xStream.fromXML(xmlStr);
        System.out.println(orderReqBack.getName());
        System.out.println(orderReqBack.getAge());
    }

    private static void testQueryReq(XStream xStream){
        QueryReq queryReq = new QueryReq();
        queryReq.setAddr("dfkljsdflj");
        queryReq.setFirstName("Sun");

        String xmlStr = xStream.toXML(queryReq);
        System.out.println(xmlStr);

        QueryReq orderReqBack = (QueryReq) xStream.fromXML(xmlStr);
        System.out.println(orderReqBack.getAddr());
        System.out.println(orderReqBack.getFirstName());
    }
}
