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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 动态构造java文件,然后编译,加载,再执行
 * <p>
 * Created by sunyiwei on 2016/11/14.
 */
public class HelloWorld {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, TemplateException, InterruptedException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, URISyntaxException {
        final String className = "com.cmcc.syw.HelloWorldDynamic";
        final String dir = "/dynamic/classes/";

        String content = buildTemplate(className);
        write(content, dir, className);

        //编译
        compile(dir, className);

        //加载
        List<String> dirs = new LinkedList<>();
        dirs.add(dir);
        Class clazz = Class.forName(className, true, new CustomClassLoader(dirs));

        //执行
        Method mainMethod = clazz.getMethod("main", String[].class);
        mainMethod.invoke(null, (Object) args);
    }

    private static void write(String content, String dir, String className) throws IOException, URISyntaxException {
        final String filename = dir + className.replace(".", "/") + ".java";
        File file = new File(filename);

        FileUtils.writeStringToFile(file, content, "utf-8");
    }

    private static boolean compile(String dir, String outputName) throws IOException, InterruptedException {
        String subPath = outputName.substring(0, outputName.lastIndexOf("."));
        String fileName = outputName.substring(outputName.lastIndexOf(".") + 1);
        Process process = Runtime.getRuntime().exec("javac " + dir + subPath.replace(".", "/") + "/" + fileName + ".java");
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

    private static class CustomClassLoader extends ClassLoader {
        private List<String> dirs = new LinkedList<>();

        public CustomClassLoader(List<String> dirs) {
            this.dirs = dirs;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            for (String dir : dirs) {
                String filename = dir + name.replace(".", "/") + ".class";
                try {
                    File file = new File(filename);

                    byte[] bytes = FileUtils.readFileToByteArray(file);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        public void addDir(String dir) {
            dirs.add(dir);
        }
    }
}
