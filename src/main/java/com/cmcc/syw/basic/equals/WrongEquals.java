package com.cmcc.syw.basic.equals;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试equals方法和hashCode方法
 * <p>
 * <p>
 * 这里只改写了equals方法，但没有改写hashCode方法，意味着这个类在作为map的key值时会有问题
 * <p>
 * Created by sunyiwei on 2016/12/21.
 */
public class WrongEquals {
    protected final String value;

    public WrongEquals(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        WrongEquals we1 = new WrongEquals("hello");
        WrongEquals we2 = new WrongEquals("world");


        Map<WrongEquals, Integer> map = new HashMap<>();
        map.put(we1, 10);
        map.put(we2, 10);

        //这里输出的值为2， 说明两个key确实都放到map中了
        System.out.println(map.size());

        //但这里输出的为null，说明根据value值查找出问题了
        System.out.println(map.get(new WrongEquals("hello")));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WrongEquals)) {
            return false;
        }

        WrongEquals we = (WrongEquals) obj;
        return we == this || value.equals(we.value);
    }
}
