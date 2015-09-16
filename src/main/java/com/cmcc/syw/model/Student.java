package com.cmcc.syw.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunyiwei on 2015/8/29.
 */
@XmlRootElement(name = "student")
public class Student {
    private String name;
    private int age;

    public Student(){

    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
