package com.cmcc.syw.reflection.mapper.service;

/**
 * 将某种风格的名字转化成另一种风格的名字
 *
 * Created by sunyiwei on 2016/11/16.
 */
public interface NameConvertService {
    /**
     * 将某种风格的名字转化成另一种风格的名字
     *
     * @param name 原始风格的名字
     * @return 目标风格的名字
     */
    String convert(String name);
}
