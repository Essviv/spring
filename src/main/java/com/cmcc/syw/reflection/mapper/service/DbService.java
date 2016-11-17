package com.cmcc.syw.reflection.mapper.service;

import com.cmcc.syw.reflection.mapper.model.DbConfig;
import com.cmcc.syw.reflection.mapper.model.TableInfo;

/**
 * 数据库服务
 *
 * Created by sunyiwei on 2016/11/16.
 */
public interface DbService {
    /**
     * 根据数据库配置信息及表名信息进行解析,得到表的字段属性信息
     *
     * @param dbConfig  数据库配置信息
     * @param tableName 表名
     */
    TableInfo parse(DbConfig dbConfig, String tableName);
}
