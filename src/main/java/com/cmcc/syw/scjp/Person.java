package com.cmcc.syw.scjp;

/**
 * Created by sunyiwei on 2016/10/10.
 */
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
