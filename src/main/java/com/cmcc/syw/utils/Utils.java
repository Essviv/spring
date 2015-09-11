package com.cmcc.syw.utils;

/**
 * Created by sunyiwei on 2015/9/8.
 */
public class Utils {
    public static long fibonaci(int n){
        n = Math.abs(n);

        if(n==0 || n==1){
            return 1;
        }

        return fibonaci(n-1) + fibonaci(n-2);
    }
}
