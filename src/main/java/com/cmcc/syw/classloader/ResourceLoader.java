package com.cmcc.syw.classloader;

import org.apache.commons.codec.Charsets;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源加载器， 测试classLoader的getResourceAsStream方法
 * <p>
 * Created by sunyiwei on 2016/11/14.
 */
public class ResourceLoader {
    public static void load(String resourcePath) throws IOException {
        InputStream is = ResourceLoader.class.getResourceAsStream(resourcePath);
        if (is != null) {
            System.out.println(StreamUtils.copyToString(is, Charsets.UTF_8));
        }
    }

    public static void main(String[] args) throws IOException {
        final String r3 = "/r3.txt";
        final String r4 = "/helloworld.ftl";

        ResourceLoader.load(r3);
        ResourceLoader.load(r4);
    }
}
