package com.cmcc.syw.scjp;

/**
 * Created by sunyiwei on 2016/10/24.
 */
public class InterfaceTestImpl implements InterfaceTest {
    public static void main(String[] args) {
        int x = 5;
        new InterfaceTestImpl().doStuff(++x);

        System.out.println(true ^ true);
        System.out.println(true ^ false);
        System.out.println(false ^ true);
        System.out.println(false ^ false);
    }

    @Override
    public void doStuff(int i) {
        i += EASY + ++i;
        System.out.println("I = " + i);
    }
}
