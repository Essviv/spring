package com.cmcc.syw.service.impl;

/**
 * 这个类在多线程执行时会有问题：
 *
 * 如果线程A执行了getRGB, 然后线程B执行了set方法，这时线程A再获取getName，得到的结果就会和之前的不一致
 *
 * Created by sunyiwei on 2016/3/18.
 */
public class SynchronizedRGB {
    private int red;
    private int green;
    private int blue;
    private String name;

    public SynchronizedRGB(int red, int green, int blue, String name) {
        check(red, green, blue);

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public static void main(String[] args) {
        SynchronizedRGB rgb = new SynchronizedRGB(0, 0, 0, "Black");
        System.out.println(rgb.getRGB());
        System.out.println(rgb.getName());
    }

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public void set(int red, int green, int blue, String name) {
        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    public int getRGB() {
        return (red << 16) | (green << 8) | blue;
    }

    public String getName() {
        return this.name;
    }
}
