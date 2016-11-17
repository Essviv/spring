package com.cmcc.syw.reflection.mapper.service;

/**
 * 将DB的数据库类型转化成JAVA的数据类型
 *
 * Created by sunyiwei on 2016/11/16.
 */
public interface TypeConvertService {
    /**
     * 将db数据类型转化成JAVA的数据类型
     *
     * @param dbType db数据类型
     * @return 类型对象
     */
    Class convert(String dbType);

    /**
     * 将db数据类型转化成JAVA的数据类型
     *
     * @param dbType db数据类型
     * @return 类型的全限定名
     */
    String convertAsString(String dbType);
}
