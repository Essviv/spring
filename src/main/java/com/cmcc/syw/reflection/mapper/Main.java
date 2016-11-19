package com.cmcc.syw.reflection.mapper;

import com.cmcc.syw.reflection.mapper.model.DbConfig;
import com.cmcc.syw.reflection.mapper.model.MapperConfig;
import com.cmcc.syw.reflection.mapper.service.DbService;
import com.cmcc.syw.reflection.mapper.service.MapperService;
import com.cmcc.syw.reflection.mapper.service.NameConvertService;
import com.cmcc.syw.reflection.mapper.service.TableInfoConvertService;
import com.cmcc.syw.reflection.mapper.service.impl.DbServiceImpl;
import com.cmcc.syw.reflection.mapper.service.impl.MapperServiceImpl;
import com.cmcc.syw.reflection.mapper.service.impl.NameConvertServiceImpl;
import com.cmcc.syw.reflection.mapper.service.impl.TableInfoConvertServiceImpl;

/**
 * 主程序
 *
 * Created by sunyiwei on 2016/11/19.
 */
public class Main {
    public static void main(String[] args) {
        DbConfig dbConfig = buildDbConfig();
        MapperConfig mapperConfig = buildMapperConfig();

        NameConvertService nameConvertService = new NameConvertServiceImpl();
        TableInfoConvertService tableInfoConvertService = new TableInfoConvertServiceImpl(mapperConfig, nameConvertService);
        DbService dbService = new DbServiceImpl(dbConfig, nameConvertService);
        MapperService mapperService = new MapperServiceImpl(dbService, tableInfoConvertService);

        final String TABLE_NAME = "account";
        mapperService.process(buildMapperConfig(), TABLE_NAME);
    }

    private static MapperConfig buildMapperConfig() {
        MapperConfig mapperConfig = new MapperConfig();
        mapperConfig.setBaseDir("/Users/sunyiwei/mappers/");

        return mapperConfig;
    }

    private static DbConfig buildDbConfig() {
        DbConfig dbConfig = new DbConfig();
        dbConfig.setDbName("sunyiwei7");
        dbConfig.setHost("172.23.27.229");
        dbConfig.setPort(3306);
        dbConfig.setUsername("sunyiwei");
        dbConfig.setPassword("7Ubn0ZYialQ49MSWxbJVLjs2d6kPmvQ1xI");

        return dbConfig;
    }
}
