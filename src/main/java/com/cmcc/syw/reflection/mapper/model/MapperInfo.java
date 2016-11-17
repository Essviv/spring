package com.cmcc.syw.reflection.mapper.model;

import java.util.List;

/**
 * 用于生成mapper的信息对象
 *
 * Created by sunyiwei on 2016/11/16.
 */
public class MapperInfo {
    //全限定类名
    private String qulifierClassName;

    //DB属性信息
    private List<ColumnInfo> cols;

    //表名
    private String tableName;

    public String getQulifierClassName() {
        return qulifierClassName;
    }

    public void setQulifierClassName(String qulifierClassName) {
        this.qulifierClassName = qulifierClassName;
    }

    public List<ColumnInfo> getCols() {
        return cols;
    }

    public void setCols(List<ColumnInfo> cols) {
        this.cols = cols;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
