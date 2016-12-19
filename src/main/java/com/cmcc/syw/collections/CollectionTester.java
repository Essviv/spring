package com.cmcc.syw.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * collection接口的测试
 *
 * Created by sunyiwei on 2016/12/19.
 */
public class CollectionTester {
    public static void main(String[] args) {
        Collection<Integer> integerCollection = new LinkedList<>();
        init(integerCollection);

        //增
        add(integerCollection);

        //查
        query(integerCollection);

        //改
        modify(integerCollection);

        //删
        remove(integerCollection);
    }

    private static void modify(Collection<Integer> oldCollection) {
        final int COUNT = 10;
        Random r = new Random();

        Collection<Integer> retainCollections = new LinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            retainCollections.add(r.nextInt(100));
        }

        oldCollection.retainAll(retainCollections);
        print(oldCollection);
    }

    private static void print(Collection<Integer> collection) {
        System.out.println(collection);
    }

    private static void query(Collection<Integer> collections) {
        Random r = new Random();

        final int COUNT = 10;
        for (int i = 0; i < COUNT; i++) {
            int value = r.nextInt(100);
            if (collections.contains(value)) {
                System.out.format("Collections contains %d.%n", value);
            }
        }

        print(collections);
    }

    private static void remove(Collection<Integer> collections) {
        final int SIZE = collections.size();
        final int COUNT = 5;

        int index = 0;
        Iterator<Integer> iter = collections.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
            index++;
        }

        print(collections);
    }

    private static void add(Collection<Integer> collections) {
        final int COUNT = 5;
        Random r = new Random();

        for (int i = 0; i < COUNT; i++) {
            collections.add(r.nextInt(100));
        }

        print(collections);
    }

    private static void init(Collection<Integer> collections) {
        final int COUNT = 10;
        Random r = new Random();

        for (int i = 0; i < COUNT; i++) {
            collections.add(r.nextInt(100));
        }

        print(collections);
    }
}
