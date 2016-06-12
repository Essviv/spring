package com.cmcc.syw.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

/**
 * Created by sunyiwei on 16/6/12.
 */
@XStreamAlias("Request")
public class Person {
    private String name;

    private Car car;

    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
