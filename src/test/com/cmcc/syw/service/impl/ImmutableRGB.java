package com.cmcc.syw.service.impl;

import java.util.List;

/**
 * Created by sunyiwei on 2016/3/18.
 */
public class ImmutableRGB {
    final private int r;
    final private int g;
    final private int b;
    final private String name;

    public ImmutableRGB(int r, int g, int b, String name, List list) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.name = name;
    }

    public int getRGB() {
        return (r << 16) | (g << 8) | b;
    }

    public String getName() {
        return name;
    }
}
