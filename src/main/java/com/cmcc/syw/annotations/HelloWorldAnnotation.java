package com.cmcc.syw.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by sunyiwei on 2016/10/25.
 */
@Target(ElementType.FIELD)
public @interface HelloWorldAnnotation {
    /**
     * 名称
     *
     * @return
     */
    String name() default "";

    /**
     * 年龄
     *
     * @return
     */
    int age() default 18;
}
