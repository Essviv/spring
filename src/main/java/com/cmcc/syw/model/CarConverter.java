package com.cmcc.syw.model;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Created by sunyiwei on 16/6/12.
 */
public class CarConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        Car car = (Car) o;

        hierarchicalStreamWriter.startNode("Car");

        hierarchicalStreamWriter.startNode("car-name");
        hierarchicalStreamWriter.setValue(car.getName());
        hierarchicalStreamWriter.endNode();

        hierarchicalStreamWriter.startNode("car-price");
        hierarchicalStreamWriter.setValue(String.valueOf(car.getPrice()));
        hierarchicalStreamWriter.endNode();

        hierarchicalStreamWriter.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        Car car = new Car();

        hierarchicalStreamReader.moveDown();
        car.setName(hierarchicalStreamReader.getValue());
        hierarchicalStreamReader.moveUp();

        hierarchicalStreamReader.moveDown();
        car.setPrice(NumberUtils.toDouble(hierarchicalStreamReader.getValue()));
        hierarchicalStreamReader.moveUp();

        return car;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Car.class);
    }
}
