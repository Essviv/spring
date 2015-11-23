package com.cmcc.syw.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by sunyiwei on 2015/11/18.
 */
@Aspect
@Component
public class AnnotationBasedAOP {
    private static final Logger LOGGER = Logger.getLogger(AnnotationBasedAOP.class);

    @Pointcut("execution(* com.cmcc.syw.controller..*.*(..))")
    private void allControllerMethods(){

    }

    @Before("allControllerMethods()")
    public void before(){
        LOGGER.error("This is output from annotationBasedAOP before...");
    }

    @After("allControllerMethods()")
    public void after(){
        LOGGER.error("This is output from annationBasedAOP after");
    }
}
