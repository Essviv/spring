package com.cmcc.syw.reflection.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 通过反射机制完成动态加载
 * <p/>
 * Created by sunyiwei on 2016/11/9.
 */
public class DynamicLoading {
    public static void main(String[] args) throws IOException {
        boolean isRunning = true;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (isRunning) {
            String input = br.readLine();
            if ("Exist".equals(input)) {
                isRunning = false;
            } else {
                //结合抽象类工厂模式与反射机制，可以动态地指定animal类的实现
                //这里是通过控制台输入，真实的环境中，可以通过配置文件等方式进行配置得到
                try {
                    Class clazz = Class.forName(input);
                    AbstractAnimalFactory animalFactory = (AbstractAnimalFactory) clazz.newInstance();
                    Animal animal = animalFactory.create();

                    animal.run();
                    animal.yield();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
