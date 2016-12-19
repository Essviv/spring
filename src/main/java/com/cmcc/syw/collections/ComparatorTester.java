package com.cmcc.syw.collections;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * comparator接口测试
 *
 * Created by sunyiwei on 2016/12/19.
 */
public class ComparatorTester {
    public static void main(String[] args) {
        ComparatorTester ct = new ComparatorTester();
        List<Integer> list = init();
        System.out.println(list);

        //sort
        ct.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        System.out.println(list);
    }

    private static List<Integer> init() {
        final int COUNT = 100;
        Random r = new Random();
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < COUNT; i++) {
            list.add(r.nextInt(1000));
        }

        return list;
    }

    public List<Integer> sort(List<Integer> oldCollection, Comparator<Integer> comparator) {
        final int SIZE = oldCollection.size();

        //冒泡算法
        for (int i = 0; i < SIZE; i++) {
            int value = oldCollection.get(i);

            for (int j = i; j < SIZE; j++) {
                int tmp = oldCollection.get(j);
                if (comparator.compare(value, tmp) > 0) {
                    swap(oldCollection, i, j);
                    value = tmp;
                }
            }
        }

        return oldCollection;
    }

    private void swap(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
