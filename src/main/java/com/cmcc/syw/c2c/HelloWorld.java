package com.cmcc.syw.c2c;

import com.google.common.base.Charsets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 动态构造java文件,然后编译,加载,再执行
 *
 * Created by sunyiwei on 2016/11/14.
 */
public class HelloWorld {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, TemplateException, InterruptedException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, URISyntaxException {
        final String className = "HelloWorldDynamic";
        String content = buildTemplate(className);
        write(content, className);

        //编译
        compile(content, className);

        //加载
        Class clazz = Class.forName(className);

        //执行
        Method mainMethod = clazz.getMethod("main", String.class);
        mainMethod.invoke(null, new String[]{});
    }

    private static void write(String content, String className) throws IOException {
        final String filename = HelloWorld.class.getPackage().getName().replace(".", "/") +  className + ".java";
        FileUtils.writeStringToFile(new File(filename), content, "utf-8");
    }

    private static boolean compile(String content, String outputName) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("javac " + outputName + ".java");
        process.waitFor();

        if (process.exitValue() != 0) { //编译失败
            System.out.println(StreamUtils.copyToString(process.getErrorStream(), Charsets.UTF_8));
        }

        return true;
    }

    private static String buildTemplate(String className) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(HelloWorld.class, "/");
        cfg.setDefaultEncoding("utf-8");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("className", className);

        Template template = cfg.getTemplate("/helloworld.ftl");
        StringWriter writer = new StringWriter();
        template.process(map, writer);

        return writer.toString();
    }
}
