package com.cmcc.syw.aop;

/**
 * Created by sunyiwei on 2015/10/19.
 */
//@Component("customAOP")
//@Aspect
public class CustomAOP {
//    @Before("target(com.cmcc.syw.service.AuthorService)")
    private void test(){
        System.out.println("This is output from customAOP...");
    }
}
