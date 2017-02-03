package com.cmcc.syw.jvm.classloader.dynamicReplacement;

/**
 * ProductImplB
 *
 * Created by sunyiwei on 2016/11/14.
 */
public class ProductImplB implements Product {
    @Override
    public void printName() {
        System.out.println("ProductImplB");
    }
}
