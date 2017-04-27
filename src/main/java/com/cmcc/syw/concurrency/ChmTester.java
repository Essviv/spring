package com.cmcc.syw.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap的测试类,测试它的transfer方法的实现
 *
 * Created by sunyiwei on 2017/4/26.
 */
public class ChmTester {
    public static void main(String[] args) {
        int ori = 0, value = 0, result = 0;
        print(ori = Integer.numberOfLeadingZeros(16));
        print(value = (1 << 15));
        print(result = ori | value);
        System.out.println(result);

        final int COUNT = 16;
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < COUNT; i++) {
            map.put("a_" + i, String.valueOf(i));
        }

        System.out.println(map.get("a_0"));
    }

    private static void print(int value){
        System.out.println(Integer.toBinaryString(value));
    }
}
