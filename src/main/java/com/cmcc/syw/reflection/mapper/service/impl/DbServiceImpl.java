package com.cmcc.syw.reflection.mapper.service.impl;

import com.cmcc.syw.reflection.mapper.model.DbConfig;
import com.cmcc.syw.reflection.mapper.model.TableInfo;
import com.cmcc.syw.reflection.mapper.service.DbService;

/**
 * 数据库解析服务
 *
 * Created by sunyiwei on 2016/11/17.
 */
public class DbServiceImpl implements DbService {
    @Override
    public TableInfo parse(DbConfig dbConfig, String tableName) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);


        return tableInfo;
    }
}
