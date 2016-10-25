package com.cmcc.syw.annotations;

import java.lang.reflect.Field;

/**
 * Created by sunyiwei on 2016/10/25.
 */
public class AppleInfoUtils {
    public static void getFruitInfo(Class clazz) {
        String fruitName = "水果名称: ";
        String fruitColor = "水果颜色: ";

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName1 = field.getAnnotation(FruitName.class);
                fruitName += fruitName1.value();
                System.out.println(fruitName);
            }

            if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitName1 = field.getAnnotation(FruitColor.class);
                fruitColor += fruitName1.value();
                System.out.println(fruitColor);
            }
        }
    }

    public static void main(String[] args) {
        AppleInfoUtils.getFruitInfo(Apple.class);
    }
}
