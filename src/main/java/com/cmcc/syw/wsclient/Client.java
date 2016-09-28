package com.cmcc.syw.wsclient;

import javax.xml.soap.*;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunyiwei on 2016/9/27.
 */
public class Client {
    public static void main(String[] args) throws SOAPException, IOException {
        long begin = System.currentTimeMillis();

        SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = factory.createConnection();

        SOAPMessage request = buildReq();
        request.writeTo(System.out);
        System.out.println();

        SOAPMessage response = connection.call(request, new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx"));
        response.writeTo(System.out);
        System.out.println();

        connection.close();
        System.out.println(TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - begin)));
    }

    private static SOAPMessage buildReq() throws SOAPException {
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage req = mf.createMessage();

        SOAPEnvelope envelope = req.getSOAPPart().getEnvelope();
        envelope.addNamespaceDeclaration("web", "http://WebXml.com.cn/");

        MimeHeaders mimeHeaders = req.getMimeHeaders();
        mimeHeaders.addHeader("SOAPAction", "http://WebXml.com.cn/getMobileCodeInfo");

        SOAPBody body = req.getSOAPBody();
        body.setPrefix(envelope.getPrefix());

        SOAPElement element = body.addChildElement("getMobileCodeInfo", "web");
        SOAPElement mobileCodeElem = element.addChildElement("mobileCode", "web");
        mobileCodeElem.addTextNode("18867102100");

        return req;
    }
}
