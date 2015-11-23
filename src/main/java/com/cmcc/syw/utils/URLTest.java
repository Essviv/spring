package com.cmcc.syw.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 * Created by sunyiwei on 2015/10/26.
 */
public class URLTest {
    public static void main(String[] args) throws URISyntaxException, IOException {
//        String str = "http://www.baidu.com:80/index.html?name=sunyiwei%3434";
//
//        URI uri = new URI(str);
//        System.out.println(uri.getHost());
//        System.out.println(uri.getPort());
//        System.out.println(uri.getQuery());
//        System.out.println(uri.getScheme());
//
//        URL url = uri.toURL();
//        System.out.println(url.getQuery());

//        testUrlResource();
        testClasspathResource();
    }


    private static void testUrlResource() throws IOException {
        String str = "ftp://ftp.bupt.edu.cn/";
        System.out.println(getContent(new UrlResource(str)));
    }

    private static void testClasspathResource() throws IOException {
        String str = "markdown.txt";
        System.out.println(getContent(new ClassPathResource(str)));
    }


    private static String getContent(Resource resource) throws IOException {
        StringBuffer sb = new StringBuffer();

        InputStream is = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String tmpStr = null;
        while((tmpStr = reader.readLine())!=null){
            sb.append(tmpStr);
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }
}
