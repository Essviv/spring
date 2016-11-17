package com.cmcc.syw.reflection.mapper.model;

/**
 * 将表的字段信息映射成mapper时的配置信息
 *
 * Created by sunyiwei on 2016/11/16.
 */
public class MapperConfig {
    //输出的目录
    private String baseDir;

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }
}

