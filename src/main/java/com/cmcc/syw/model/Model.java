package com.cmcc.syw.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunyiwei on 16/9/29.
 */
@XmlRootElement(name = "Model")
public class Model {
    private String name;
    private int age;
    private String gender;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
