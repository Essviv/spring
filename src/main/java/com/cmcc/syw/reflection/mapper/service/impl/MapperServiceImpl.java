package com.cmcc.syw.reflection.mapper.service.impl;

import com.google.gson.Gson;

import com.cmcc.syw.reflection.mapper.model.MapperConfig;
import com.cmcc.syw.reflection.mapper.model.MapperInfo;
import com.cmcc.syw.reflection.mapper.model.TableInfo;
import com.cmcc.syw.reflection.mapper.service.DbService;
import com.cmcc.syw.reflection.mapper.service.MapperService;
import com.cmcc.syw.reflection.mapper.service.TableInfoConvertService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 映射处理服务的实现类
 *
 * Created by sunyiwei on 2016/11/16.
 */
public class MapperServiceImpl implements MapperService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperServiceImpl.class);
    //mapper xml的文件名
    private final String MAPPER_XML_FILE_NAME = "mapper_xml.ftl";

    //mapper java的文件名
    private final String MAPPER_JAVA_FILE_NAME = "mapper_java.ftl";

    //DB配置服务
    private DbService dbService;

    //表信息的转化服务
    private TableInfoConvertService tableInfoConvertService;

    public MapperServiceImpl(DbService dbService, TableInfoConvertService tableInfoConvertService) {
        this.dbService = dbService;
        this.tableInfoConvertService = tableInfoConvertService;
    }

    @Override
    public boolean process(MapperConfig mapperConfig, String tableName) {
        Gson gson = new Gson();

        //1. 根据数据库配置信息及表名信息获取到表相应的字段信息
        TableInfo tableInfo = dbService.parse(tableName);
        if (tableInfo == null || tableInfo.getCols() == null || tableInfo.getCols().isEmpty()) {
            LOGGER.error("无法根据数据库的配置信息及表名信息获取到相应的字段信息. TableName = {}.", gson.toJson(tableName));
            return false;
        }

        //2. 根据表信息生成相应的映射信息
        MapperInfo mapperInfo = tableInfoConvertService.convert(tableInfo);
        if (mapperInfo == null) {
            LOGGER.error("无法根据解析的表信息转化成相应的映射信息. TableInfo = {}.", gson.toJson(tableInfo));
            return false;
        }

        //3. 根据映射信息生成相应的文件
        return generateCode(MAPPER_XML_FILE_NAME, mapperInfo, mapperConfig);
    }

    /**
     * 根据映射配置信息和配置信息输出相应的代码文件
     *
     * @param mapperInfo   映射信息
     * @param mapperConfig 映射配置
     * @return 成功生成文件则返回true, 否则false
     */
    private boolean generateCode(String filename, MapperInfo mapperInfo, MapperConfig mapperConfig) {
        //初始化FTL的配置
        Configuration cfg = initCfg();
        String baseDir = mapperConfig.getBaseDir();

        String content = resolve(cfg, mapperInfo, MAPPER_XML_FILE_NAME);
        try {
            FileUtils.writeStringToFile(new File(baseDir, filename), content, "utf-8");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Configuration initCfg() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");

        return configuration;
    }

    private String resolve(Configuration cfg, MapperInfo mapperInfo, String templateName) {

        try {
            Template template = cfg.getTemplate(templateName);

            StringWriter stringWriter = new StringWriter();
            Map<String, Object> model = new LinkedHashMap<>();
            model.put("mapperInfo", mapperInfo);
            template.process(model, stringWriter);

            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
