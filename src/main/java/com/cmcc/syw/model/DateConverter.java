package com.cmcc.syw.model;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import org.apache.commons.lang.math.NumberUtils;

import java.util.Date;

/**
 * Created by sunyiwei on 16/6/14.
 */
public class DateConverter implements Converter {
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Date date = (Date) source;
        writer.setValue(String.valueOf(date.getTime()));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String value = null;

        reader.moveDown();
        value = reader.getValue();
        reader.moveUp();

        return new Date(NumberUtils.toLong(value));
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Date.class);
    }
}
