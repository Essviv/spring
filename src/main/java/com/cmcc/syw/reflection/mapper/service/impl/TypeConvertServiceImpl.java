package com.cmcc.syw.reflection.mapper.service.impl;

import com.cmcc.syw.reflection.mapper.service.TypeConvertService;

import java.util.Date;

/**
 * 将数据库类型转化成JAVA类型
 *
 * Created by sunyiwei on 2016/11/17.
 */
public class TypeConvertServiceImpl implements TypeConvertService {
    @Override
    public Class convert(String dbType) {
        if (dbType.contains("VARCHAR")) {
            return String.class;
        } else if (dbType.contains("bigint")) {
            return long.class;
        } else if (dbType.contains("int")) {
            return long.class;
        } else if (dbType.contains("double")) {
            return double.class;
        } else if (dbType.contains("float")) {
            return float.class;
        } else if (dbType.contains("datetime")) {
            return Date.class;
        }

        return null;
    }

    @Override
    public String convertAsString(String dbType) {
        return convert(dbType).getName();
    }
}
