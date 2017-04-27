package com.cmcc.syw.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 测试hashMap的实现
 *
 * Created by sunyiwei on 2017/4/26.
 */
public class HashMapTester {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>((int)(4. / 0.75));
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");

        System.out.println(map.get("a"));
    }
}
