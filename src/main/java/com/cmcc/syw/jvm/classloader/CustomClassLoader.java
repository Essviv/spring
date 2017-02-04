package com.cmcc.syw.jvm.classloader;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 自定义类加载器，从指定的目录下获取相应的文件
 * <p>
 * Created by patrick on 2017/2/4.
 */
public class CustomClassLoader {
    /**
     * CustomClassLoader dir
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        int count = args.length;
        if (count != 1) {
            return;
        }

        //自定义类加载器
        String dir = args[0];

        ClassLoader classLoader = new ClassLoader(CustomClassLoader.class.getClassLoader()) {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.replace('.', '\\') + ".class";
                    byte[] bytes = FileUtils.readFileToByteArray(new File(dir, fileName));
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        String className = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (!"exit".equalsIgnoreCase(className)) {
            className = bufferedReader.readLine();

            try {
                Class clazz = classLoader.loadClass(className);
                System.out.println(clazz.getName());
            } catch (ClassNotFoundException ignored) {
            }
        }
    }
}
