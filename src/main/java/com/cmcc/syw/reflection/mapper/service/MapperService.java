package com.cmcc.syw.reflection.mapper.service;

import com.cmcc.syw.reflection.mapper.model.MapperConfig;

/**
 * 映射处理服务
 *
 * Created by sunyiwei on 2016/11/16.
 */
public interface MapperService {
    /**
     * 根据提供的数据库配置信息,映射配置信息以及表名生成相应的mapper
     *
     * @param mapperConfig 映射配置信息
     * @param tableName    表名信息
     */
    boolean process(MapperConfig mapperConfig, String tableName);
}
