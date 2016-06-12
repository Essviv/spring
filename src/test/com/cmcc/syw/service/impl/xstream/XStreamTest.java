package com.cmcc.syw.service.impl.xstream;

import com.alibaba.fastjson.JSONObject;
import com.cmcc.syw.model.Car;
import com.cmcc.syw.model.CarConverter;
import com.cmcc.syw.model.Person;
import com.cmcc.syw.model.PersonConverter;
import com.thoughtworks.xstream.XStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

/**
 * Created by sunyiwei on 16/6/12.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class XStreamTest {
    @Test
    public void testConverter() throws Exception {
        XStream xstream = new XStream();
        xstream.alias("Person", Person.class);
        xstream.registerConverter(new PersonConverter());
        xstream.registerConverter(new CarConverter());

        System.out.println(xstream.toXML(buildPerson()));
    }

    @Test
    public void testObjectOutputStream() throws Exception {
        XStream xstream = new XStream();
        xstream.alias("Person", Person.class);
        xstream.registerConverter(new PersonConverter());
        xstream.registerConverter(new CarConverter());

        ObjectOutputStream oos = xstream.createObjectOutputStream(System.out);
        oos.writeObject(buildPerson());
        oos.close();
    }

    @Test
    public void testDuplicateAnnotation() throws Exception {
        XStream xstream = new XStream();
        xstream.processAnnotations(Person.class);
        xstream.processAnnotations(Car.class);

        System.out.println(xstream.toXML(buildPerson()));
        System.out.println(xstream.toXML(buildCar()));
    }

    @Test
    public void testObjectInputStream() throws Exception {
        XStream xstream = new XStream();
        xstream.alias("Person", Person.class);
        xstream.registerConverter(new PersonConverter());
        xstream.registerConverter(new CarConverter());

        StringWriter stringWriter = new StringWriter();
        ObjectOutputStream oos = xstream.createObjectOutputStream(stringWriter);
        oos.writeObject(buildPerson());
        oos.close();

        ObjectInputStream ois = xstream.createObjectInputStream(new StringReader(stringWriter.toString()));
        Person person = (Person)ois.readObject();
        System.out.println(JSONObject.toJSONString(person));
    }

    @Test
    public void testDeserialize() throws Exception {
        final String XML = "<Person>\n" +
                "  <person-name>Patrick</person-name>\n" +
                "  <Car>\n" +
                "    <car-name>BMW</car-name>\n" +
                "    <car-price>300000.0</car-price>\n" +
                "  </Car>\n" +
                "  <person-birthday>1465739120474</person-birthday>\n" +
                "</Person>";


        XStream xstream = new XStream();
        xstream.alias("Person", Person.class);
        xstream.registerConverter(new PersonConverter());
        xstream.registerConverter(new CarConverter());

        Person person = (Person) xstream.fromXML(XML);
        System.out.println(JSONObject.toJSONString(person));
    }

    private Car buildCar() {
        Car car = new Car();

        car.setName("BMW");
        car.setPrice(300000.d);

        return car;
    }

    private Person buildPerson() {
        Person person = new Person();
        person.setBirthday(new Date());
        person.setName("Patrick");
        person.setCar(buildCar());

        return person;
    }
}
