package com.cmcc.syw.reflection.mapper;

import com.cmcc.syw.reflection.mapper.model.ColumnInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ftl测试器
 *
 * Created by sunyiwei on 2016/11/19.
 */
public class FtlTester {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = initCfg();
        Template template = cfg.getTemplate("test2.ftl");

        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new LinkedHashMap<>();
        model.put("col", build());
        template.process(model, stringWriter);

        System.out.println(stringWriter.toString());
    }

    private static ColumnInfo build() {
        ColumnInfo ci = new ColumnInfo();
        ci.setPrimary(true);
        ci.setColName("id");
        ci.setPropName("id");
        ci.setType("java.lang.String");

        return ci;
    }

    private static Configuration initCfg() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(FtlTester.class, "/templates");

        return configuration;
    }
}
