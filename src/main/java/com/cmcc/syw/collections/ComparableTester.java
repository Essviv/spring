package com.cmcc.syw.collections;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * comparable接口的测试
 *
 * Created by sunyiwei on 2016/12/19.
 */
public class ComparableTester implements Comparable<Integer> {
    private final int value;

    public ComparableTester(int value) {
        this.value = value;
    }


    private static void sort(List<ComparableTester> cts) {
        final int SIZE = cts.size();
        for (int i = 0; i < SIZE; i++) {
            ComparableTester ct = cts.get(i);
            for (int j = i + 1; j < SIZE; j++) {
                ComparableTester tmp = cts.get(j);
                if (ct.compareTo(tmp.getValue()) > 0) {
                    swap(cts, i, j);
                    ct = cts.get(i);
                }
            }
        }
    }

    private static void swap(List<ComparableTester> list, int i, int j) {
        ComparableTester tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    private static List<ComparableTester> buildCts() {
        final int COUNT = 100;
        List<ComparableTester> cts = new LinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            cts.add(build());
        }

        return cts;
    }

    private static ComparableTester build() {
        Random r = new Random();
        return new ComparableTester(r.nextInt(10000));
    }

    public static void main(String[] args) {
        List<ComparableTester> cts = buildCts();
        System.out.println(cts);

        //sort
        sort(cts);
        System.out.println(cts);
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Integer o) {
//        System.out.format("Value = %s, O = %s. %n", value, o);
        return value - o;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
