package com.cmcc.syw.reflection.mapper.service.impl;

import com.cmcc.syw.reflection.mapper.model.ColumnInfo;
import com.cmcc.syw.reflection.mapper.model.DbConfig;
import com.cmcc.syw.reflection.mapper.model.TableInfo;
import com.cmcc.syw.reflection.mapper.service.DbService;
import com.cmcc.syw.reflection.mapper.service.NameConvertService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据库解析服务
 *
 * Created by sunyiwei on 2016/11/17.
 */
public class DbServiceImpl implements DbService {
    private DbConfig dbConfig;

    private NameConvertService nameConvertService;

    public DbServiceImpl(DbConfig dbConfig, NameConvertService nameConvertService) {
        this.dbConfig = dbConfig;
        this.nameConvertService = nameConvertService;
    }

    @Override
    public TableInfo parse(String tableName) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);

        Connection con = connect(dbConfig);
        if (con == null) {
            return null;
        }

        List<ColumnInfo> cis = getDbStructure(con, tableName);
        tableInfo.setCols(cis);

        return tableInfo;
    }

    private List<ColumnInfo> getDbStructure(Connection connection, String tableName) {
        try {
            PreparedStatement statement = connection.prepareStatement("show COLUMNS from " + tableName);

            ResultSet rs = statement.executeQuery();
            List<ColumnInfo> cis = new LinkedList<>();
            while (rs.next()) {
                ColumnInfo ci = new ColumnInfo();

                String colName = rs.getString(1);
                ci.setColName(colName);
                ci.setPropName(nameConvertService.convert(colName));
                ci.setType(parseColType(rs.getString(2)));
                ci.setPrimary(rs.getString(4).contains("PRI"));

                cis.add(ci);
            }

            return cis;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    private String parseColType(String type) {
        int startIndex = type.indexOf("(");

        if (startIndex != -1) {
            type =  type.substring(0, startIndex);
        }

        return type.toUpperCase();
    }

    private Connection connect(DbConfig dbConfig) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            final String url = "jdbc:mysql://" + dbConfig.getHost() + ":" + dbConfig.getPort() + "/" + dbConfig.getDbName()
                    + "?useUnicode=true";
            return DriverManager.getConnection(url, dbConfig.getUsername(), dbConfig.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
