package com.cmcc.syw.reflection.mapper.model;

import java.util.List;

/**
 * 表信息
 *
 * Created by sunyiwei on 2016/11/16.
 */
public class TableInfo {
    //表名信息
    private String tableName;

    //字段属性信息
    private List<ColumnInfo> cols;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnInfo> getCols() {
        return cols;
    }

    public void setCols(List<ColumnInfo> cols) {
        this.cols = cols;
    }
}
