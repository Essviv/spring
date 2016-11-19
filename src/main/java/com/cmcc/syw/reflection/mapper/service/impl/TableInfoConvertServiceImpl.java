package com.cmcc.syw.reflection.mapper.service.impl;

import com.cmcc.syw.reflection.mapper.model.MapperConfig;
import com.cmcc.syw.reflection.mapper.model.MapperInfo;
import com.cmcc.syw.reflection.mapper.model.TableInfo;
import com.cmcc.syw.reflection.mapper.service.NameConvertService;
import com.cmcc.syw.reflection.mapper.service.TableInfoConvertService;

/**
 * 表信息的转化服务
 *
 * Created by sunyiwei on 2016/11/17.
 */
public class TableInfoConvertServiceImpl implements TableInfoConvertService {
    //映射配置
    private MapperConfig mapperConfig;

    //名称转化服务
    private NameConvertService nameConvertService;

    public TableInfoConvertServiceImpl(MapperConfig mapperConfig, NameConvertService nameConvertService) {
        this.mapperConfig = mapperConfig;
        this.nameConvertService = nameConvertService;
    }

    @Override
    public MapperInfo convert(TableInfo tableInfo) {
        MapperInfo mapperInfo = new MapperInfo();
        mapperInfo.setTableName(tableInfo.getTableName());
        mapperInfo.setCols(tableInfo.getCols());
        mapperInfo.setQulifierClassName(mapperConfig.getBaseDir() + nameConvertService.convert(tableInfo.getTableName()));

        return mapperInfo;
    }
}
