package com.cmcc.syw.guava;

import com.google.common.collect.ImmutableSet;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by sunyiwei on 2016/10/19.
 */
public class CollectionPractise {
    public static void main(String[] args) {
        ImmutableSet<String> set = ImmutableSet.of(
                "a",
                "b",
                "c",
                "d");

        Set<String> bars = new LinkedHashSet<String>();
        bars.add("sf");
        bars.add("fdsadf");

        System.out.println(bars);

        ImmutableSet<String> barSet = ImmutableSet.copyOf(bars);

        System.out.println(barSet);

        bars.remove("sf");
        System.out.println(barSet);
        System.out.println(bars);

        ImmutableSet<String> buildSet = ImmutableSet.<String>builder().addAll(bars).add("adda").build();
        System.out.println(buildSet);
    }
}
