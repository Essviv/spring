package com.cmcc.syw.annotations;

/**
 * Created by sunyiwei on 2016/10/25.
 */
public class Apple {
    @FruitName("apple")
    private String name;

    @FruitColor(FruitColor.Color.RED)
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
