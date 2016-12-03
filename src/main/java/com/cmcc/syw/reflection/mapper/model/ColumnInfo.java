package com.cmcc.syw.reflection.mapper.model;

/**
 * 数据库表中的字段信息
 *
 * Created by sunyiwei on 2016/11/16.
 */
public class ColumnInfo {
    //数据库字段名称
    private String colName;

    //类属性名称
    private String propName;

    //字段类型
    private String type;

    //是否为主键
    private boolean primary;


    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
