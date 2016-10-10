package com.cmcc.syw.scjp;

import java.util.List;
import java.util.TreeSet;

/**
 * Created by sunyiwei on 2016/10/10.
 */
public class Tester {
    public static void main(String[] args) {
        test2();
    }

    public static int sum(List<Integer> list) {
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }

        return sum;
    }

    public static void test2() {
        TreeSet<Integer> s = new TreeSet<Integer>();
        TreeSet<Integer> subs = new TreeSet<Integer>();

        for (int i = 606; i < 613; i++) {
            if (i % 2 == 0) {
                s.add(i);
            }
        }

        subs = (TreeSet<Integer>) s.subSet(608, true, 611, true);
        s.add(629);

        System.out.println(s + "" + subs);

    }
}
