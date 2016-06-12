package com.cmcc.syw.utils;

import com.cmcc.syw.model.Json;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunyiwei on 2015/8/20.
 */
public class Test {
    public static void main(String[] args) throws JAXBException, IOException, TemplateException {
        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */
        final String filename = "templates/test.ftl";

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassLoaderForTemplateLoading(Test.class.getClassLoader(), "");

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        /* ------------------------------------------------------------------------ */
        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */

        /* Create a data-model */
        Map root = new HashMap();
        root.put("user", "Big Joe");
        Map latest = new HashMap();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate(filename);

        /* Merge data-model with template */
        Writer out = new OutputStreamWriter(new FileOutputStream("/tmp/testOut.html"));
        temp.process(root, out);
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }

    private static void marshal(String filename) throws JAXBException {
        Json json = new Json();

        JAXBContext context = JAXBContext.newInstance(Json.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(json, new File(filename));
    }

    private static void unmarshal(String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Json.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Json json = (Json) unmarshaller.unmarshal(new File(filename));
        System.out.println(json);
    }
}
