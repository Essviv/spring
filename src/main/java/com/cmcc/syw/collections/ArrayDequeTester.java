package com.cmcc.syw.collections;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 测试arrayDeque的方法
 * <p>
 * Created by patrick on 2016/12/27.
 */
public class ArrayDequeTester {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>(5);
        deque.addFirst("string");
        System.out.println(deque);
    }
}