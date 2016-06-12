package com.cmcc.syw.model;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import org.apache.commons.lang.math.NumberUtils;

import java.util.Date;

/**
 * Created by sunyiwei on 16/6/12.
 */
public class PersonConverter implements Converter{
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        Person person = (Person)o;

        hierarchicalStreamWriter.startNode("person-name");
        hierarchicalStreamWriter.setValue(person.getName());
        hierarchicalStreamWriter.endNode();

        CarConverter carConverter = new CarConverter();
        carConverter.marshal(person.getCar(), hierarchicalStreamWriter, marshallingContext);

        hierarchicalStreamWriter.startNode("person-birthday");
        hierarchicalStreamWriter.setValue(String.valueOf(person.getBirthday().getTime()));
        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        Person person = new Person();

        hierarchicalStreamReader.moveDown();
        person.setName(hierarchicalStreamReader.getValue());
        hierarchicalStreamReader.moveUp();

        hierarchicalStreamReader.moveDown();
        CarConverter carConverter = new CarConverter();
        person.setCar((Car)carConverter.unmarshal(hierarchicalStreamReader, unmarshallingContext));
        hierarchicalStreamReader.moveUp();

        hierarchicalStreamReader.moveDown();
        person.setBirthday(new Date(NumberUtils.toLong(hierarchicalStreamReader.getValue())));
        hierarchicalStreamReader.moveUp();

        return person;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Person.class);
    }
}
