package com.cmcc.syw.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by sunyiwei on 16/9/9.
 */
public class HashCodeTest {
    private int age;
    private String name;

    public static void main(String[] args) {
        HashCodeTest hct1 = new HashCodeTest();
        hct1.setAge(10);
        hct1.setName("sunyiwei");

        HashCodeTest hct2 = new HashCodeTest();
        hct2.setAge(10);
        hct2.setName("sunyiwei");

        System.out.println("Equals: " + hct1.equals(hct2));

        HashMap<HashCodeTest, Integer> map = new LinkedHashMap<>();
        map.put(hct1, 10);
        System.out.println(map.get(hct2));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HashCodeTest)) {
            return false;
        }

        HashCodeTest hct = (HashCodeTest) obj;
        return hct.getAge() == age && hct.getName().equals(name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result + 31 * age;
        result = result + 31 * name.hashCode();

        return result;
    }
}
