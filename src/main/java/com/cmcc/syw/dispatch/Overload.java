package com.cmcc.syw.dispatch;

/**
 * 演示静态多分派的原理
 *
 * Created by sunyiwei on 2016/11/16.
 */
public class Overload {
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        Overload overload = new Overload();

        //在编译时,sayHello方法指向的是符号引用,该符号引用的确定由方法的接收者和参数的静态类型共同决定(静态多分派)
        //方法的接收者为Overload, 参数的静态类型为Human, 因此选用了sayHello(Human)这个版本
        //在运行时,由于sayHello方法不是虚方法,因此它的符号引用会被解析成直接引用,这时候的解析只由方法的接收者的实际类型决定(动态单分派)
        //方法的接收者为Overload, 因此最终调用的是sayHello(Human), 输出的是hello, guy.
        overload.sayHello(man);
        overload.sayHello(woman);
    }

    public void sayHello(Human human) {
        System.out.println("Hello, guy.");
    }

    public void sayHello(Man man) {
        System.out.println("Hello, man.");
    }

    public void sayHello(Woman woman) {
        System.out.println("Hello, woman.");
    }

    private static class Human {
    }

    private static class Man extends Human {
    }

    private static class Woman extends Human {
    }
}
