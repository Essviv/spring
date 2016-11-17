package com.cmcc.syw.reflection.mapper.service.impl;

import com.cmcc.syw.reflection.mapper.service.NameConvertService;

import org.apache.commons.lang.StringUtils;

/**
 * 名称转化服务
 *
 * Created by sunyiwei on 2016/11/17.
 */
public class NameConvertServiceImpl implements NameConvertService {
    @Override
    public String convert(String name) {
        String[] comps = name.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        for (String comp : comps) {
            stringBuilder.append(StringUtils.capitalize(comp));
        }

        return stringBuilder.toString();
    }
}
