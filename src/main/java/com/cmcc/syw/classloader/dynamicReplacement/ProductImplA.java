package com.cmcc.syw.classloader.dynamicReplacement;

/**
 * ProductImplA
 *
 * Created by sunyiwei on 2016/11/14.
 */
public class ProductImplA implements Product {
    @Override
    public void printName() {
        System.out.println("I'm productImplA.");
    }
}
