package com.cmcc.syw.reflection.mapper.service.impl;

import com.cmcc.syw.reflection.mapper.service.TypeConvertService;

/**
 * 将数据库类型转化成JAVA类型
 *
 * Created by sunyiwei on 2016/11/17.
 */
public class TypeConvertServiceImpl implements TypeConvertService {
    @Override
    public Class convert(String dbType) {
        if("VARCHAR".equals(dbType)){
            return String.class;
        }

        return null;
    }

    @Override
    public String convertAsString(String dbType) {
        return null;
    }
}
