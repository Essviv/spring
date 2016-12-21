package com.cmcc.syw.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试hashMap的resize方法
 * <p>
 * Created by sunyiwei on 2016/12/21.
 */
public class HashMapResize {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>(8);

        for (int i = 0; i < 17; i++) {
            map.put("hello_" + i, 10);
        }

        System.out.println(map);
    }
}
