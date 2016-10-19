package com.cmcc.syw.proxy;

/**
 * 老师类的静态代理类
 * <p/>
 * Created by sunyiwei on 2016/10/19.
 */
public class StaticTeacherProxy implements IPerson {
    private Teacher teacher;

    //静态代理类内部需维持一个委托类的实例
    public StaticTeacherProxy(Teacher teacher) {
        this.teacher = teacher;
    }

    public static void main(String[] args) {
        IPerson person = new StaticTeacherProxy(new Teacher());
        person.walk();
    }

    @Override
    public void walk() {
        //add output
        System.out.println("I'm a static teacher proxy.");

        teacher.walk();

        //add output
        System.out.println("Teacher completes walk.");
    }
}
