package com.cmcc.syw.jvm.statics;

/**
 * 用于测试类加载的“初始化”阶段
 * <p>
 * Created by patrick on 2017/2/3.
 */
public class Child extends Super {
    public static int b = a;

    public static void main(String[] args) {
        System.out.println(Child.b);
    }
}

class Super {
    static {
        a = 5;
    }

    protected static int a = 3;
}
