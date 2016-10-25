package com.cmcc.syw.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 水果颜色
 * <p/>
 * Created by sunyiwei on 2016/10/25.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FruitColor {
    Color value() default Color.GREEN;

    enum Color {
        RED, GREEN, BLUE
    }
}
