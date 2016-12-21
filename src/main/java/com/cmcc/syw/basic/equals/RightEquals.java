package com.cmcc.syw.basic.equals;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示正确地重写equals和hashCode的方法
 * <p>
 * Created by sunyiwei on 2016/12/21.
 */
public class RightEquals extends WrongEquals {
    public RightEquals(String value) {
        super(value);
    }

    public static void main(String[] args) {
        RightEquals we1 = new RightEquals("hello");
        RightEquals we2 = new RightEquals("world");


        Map<WrongEquals, Integer> map = new HashMap<>();
        map.put(we1, 10);
        map.put(we2, 10);

        //这里输出的值为2， 说明两个key确实都放到map中了
        System.out.println(map.size());

        //但这里输出的不为null，说明根据value值查找正常了
        System.out.println(map.get(new RightEquals("hello")));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
