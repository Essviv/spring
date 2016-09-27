package cn.com.webxml;

import java.util.concurrent.TimeUnit;

/**
 * Created by sunyiwei on 2016/9/27.
 */
public class Client {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();

        MobileCodeWS mobileCodeWS = new MobileCodeWS();
        MobileCodeWSSoap soap = mobileCodeWS.getMobileCodeWSSoap();
        String result = soap.getMobileCodeInfo("18867102100", null);

        System.out.println(result);
        System.out.println(TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - begin)));
    }
}
