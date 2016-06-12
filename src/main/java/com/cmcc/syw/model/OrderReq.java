package com.cmcc.syw.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by sunyiwei on 2015/12/13.
 */
@XStreamAlias(("Request"))
public class OrderReq {
    @XStreamAlias("Name")
    private String name;

    @XStreamAlias("Age")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
