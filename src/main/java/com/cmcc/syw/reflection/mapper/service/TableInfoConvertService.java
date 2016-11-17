package com.cmcc.syw.reflection.mapper.service;

import com.cmcc.syw.reflection.mapper.model.MapperInfo;
import com.cmcc.syw.reflection.mapper.model.TableInfo;

/**
 * 属性字段的转换服务
 *
 * Created by sunyiwei on 2016/11/16.
 */
public interface TableInfoConvertService {
    /**
     * 将解析到表信息转化成相应的映射信息
     *
     * @param tableInfo 表信息
     * @return 映射信息
     */
    MapperInfo convert(TableInfo tableInfo);
}
