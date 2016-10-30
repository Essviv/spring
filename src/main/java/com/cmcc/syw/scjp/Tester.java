package com.cmcc.syw.scjp;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by sunyiwei on 2016/10/10.
 */
public class Tester {
    public static void main(String[] args) {
        test();
//        test2();
//        test3();
        test();
    }

    public static void test() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });

        t.start();
        t.start();
//        stringTest();
//        intTest();
    }

    public static void test(){
//        File dir = new File("dir");
//        File f = new File(dir, "f");
        boolean assertsOn = false;
        assert(assertsOn): assertsOn = true;

        if(assertsOn){
            System.out.println("Asserts is on");
        }
    }


    public static void intTest() {
        Integer x = 400;
        Integer y = x;
        x++;

        System.out.println(x == y);
        System.out.println(x);
        System.out.println(y);
    }

    public static void stringTest() {
        String str = "320";
        str += 32;

        System.out.println(str);
    }

    public static int sum(List<Integer> list) {
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }

        return sum;
    }

    public static void test3() {
        Map<Person, Integer> map = new LinkedHashMap<Person, Integer>();
        map.put(new Person("sunyiwei"), 5);
        map.put(new Person("lisa"), 6);
        map.put(new Person("lisa"), 7);

        System.out.println(map);
        System.out.println(map.size());

        System.out.println(map.get(new Person("sunyiwei")));
    }

    public static void test2() {
        TreeSet<Integer> s = new TreeSet<Integer>();
        TreeSet<Integer> subs = new TreeSet<Integer>();

        for (int i = 606; i < 613; i++) {
            if (i % 2 == 0) {
                s.add(i);
            }
        }

        subs = (TreeSet<Integer>) s.subSet(606, true, 633, true);
        s.add(629);

        System.out.println(s + "" + subs);

    }
}
