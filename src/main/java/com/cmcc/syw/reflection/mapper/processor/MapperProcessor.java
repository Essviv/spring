package com.cmcc.syw.reflection.mapper.processor;

import com.cmcc.syw.annotations.Primary;
import com.cmcc.syw.model.Account;
import com.cmcc.syw.reflection.mapper.annotations.OutputInfo;
import com.cmcc.syw.reflection.mapper.enums.FileTypes;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 处理器,根据指定的类文件生成相应的mapper.xml以及mapper.java
 *
 * Created by sunyiwei on 2016/11/15.
 */
public class MapperProcessor {
    //类名
    private final String SIMPLE_CLASS_NAME = "className";

    //包名+类名
    private final String QUALIFIED_CLASS_NAME = "qualifiedClassName";

    //包名
    private final String PACKAGE_NAME = "packageName";

    //表名
    private final String TABLE_NAME = "tableName";

    //完整的属性列表
    private final String ALL_PROPERTIES = "properties";

    //除了主键属性外的其它属性列表
    private final String PROPERTIES_WITHOUT_PRIMARY = "propertiesWithoutPrimary";

    //主键属性
    private final String PRIMARY_PROPERTY = "primaryPropertyName";

    //mapper xml的文件名
    private final String MAPPER_XML_FILE_NAME = "mapper_xml.ftl";

    //mapper java的文件名
    private final String MAPPER_JAVA_FILE_NAME = "mapper_java.ftl";

    public static void main(String[] args) {
        MapperProcessor mp = new MapperProcessor();
        mp.process(Account.class);
    }

    public void process(Class clazz) {
        //获取输出目录
        String baseDir = parseBaseDir(clazz);

        //获取FTL配置信息
        Configuration cfg = initCfg();

        //获取数据对象
        Map<String, String> model = parseData(clazz);

        //输出XML文件
        processInternal(cfg, model, clazz, baseDir, MAPPER_XML_FILE_NAME, FileTypes.XML);

        //输出JAVA文件
        processInternal(cfg, model, clazz, baseDir, MAPPER_JAVA_FILE_NAME, FileTypes.JAVA);
    }

    /**
     * 处理模板文件,并输出到指定的目录
     *
     * @param cfg          FTL的配置
     * @param model        数据模型
     * @param clazz        类信息
     * @param templateName 模板文件名称
     * @param fileTypes    输出的文件类型
     */
    private void processInternal(Configuration cfg, Map<String, String> model, Class clazz,
                                 String baseDir, String templateName, FileTypes fileTypes) {
        try {
            String xmlContent = resolve(cfg, model, templateName);
            if (StringUtils.isBlank(xmlContent)) {
                throw new RuntimeException("Template resolver returns empty content.");
            }

            output(xmlContent, baseDir, parseOutputFilename(clazz, fileTypes));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //解析输出的目录,默认为src/main/java
    private String parseBaseDir(Class clazz) {
        final String DEFAULT_BASE_DIR = "src/main/java";
        OutputInfo outputInfo = (OutputInfo) clazz.getAnnotation(OutputInfo.class);
        if (outputInfo == null || StringUtils.isBlank(outputInfo.baseDir())) {
            return DEFAULT_BASE_DIR;
        }

        return outputInfo.baseDir();
    }


    private void output(String content, String dir, String outputFileName) throws IOException {
        FileUtils.writeStringToFile(new File(dir, outputFileName), content);
    }

    private Configuration initCfg() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");

        return configuration;
    }

    private String resolve(Configuration cfg, Map<String, String> model, String templateName) {

        try {
            Template template = cfg.getTemplate(templateName);

            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);

            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据提供的类获取各种参数信息
     *
     * @param clazz 类信息
     * @return 各种名称信息
     */
    private Map<String, String> parseData(Class clazz) {
        String className = clazz.getSimpleName();
        String qulifiedName = clazz.getName();
        String packageName = clazz.getPackage().getName();

        Map<String, String> names = new LinkedHashMap<>();
        names.put(SIMPLE_CLASS_NAME, className);
        names.put(QUALIFIED_CLASS_NAME, qulifiedName);
        names.put(PACKAGE_NAME, packageName);
        names.put(TABLE_NAME, StringUtils.capitalize(className));
        names.putAll(parseProps(clazz));

        return names;
    }

    /**
     * 分析属性信息
     *
     * @param clazz 类信息
     * @return 属性相关的信息
     */
    private Map<String, String> parseProps(Class clazz) {
        Map<String, String> names = new LinkedHashMap<>();
        List<String> allProps = new LinkedList<>();
        List<String> propsExcludePrimary = new LinkedList<>();
        String primaryPropertyName = null;

        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            String fName = f.getName();
            allProps.add(fName);

            Primary isPrimary = f.getAnnotation(Primary.class);
            if (isPrimary == null) { //not primary
                propsExcludePrimary.add(fName);
            } else {
                if (StringUtils.isBlank(primaryPropertyName)) {
                    primaryPropertyName = fName;
                } else {
                    throw new RuntimeException("There should be exactly one primary property.");
                }
            }
        }

        if (StringUtils.isBlank(primaryPropertyName)) {
            throw new RuntimeException("There should be exactly one primary property.");
        }

        names.put(ALL_PROPERTIES, concat(allProps));
        names.put(PROPERTIES_WITHOUT_PRIMARY, concat(propsExcludePrimary));
        names.put(PRIMARY_PROPERTY, primaryPropertyName);

        return names;
    }

    private String concat(List<String> strs) {
        return String.join(",", strs);
    }

    /**
     * 解析输出的文件信息,包括模板名称和生成的文件名称
     *
     * @param clazz     类信息
     * @param fileTypes 文件类型
     * @return 文件信息
     */
    private String parseOutputFilename(Class clazz, FileTypes fileTypes) {
        Package packageInfo = clazz.getPackage();
        String packageName = packageInfo.getName();
        packageName= packageName.substring(0, packageName.lastIndexOf("."));

        return packageName.replace(".", "/")
                + "/" + clazz.getSimpleName()
                + "." + fileTypes.name().toLowerCase();
    }
}
