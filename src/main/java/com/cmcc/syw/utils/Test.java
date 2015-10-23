package com.cmcc.syw.utils;

import com.cmcc.syw.model.Json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by sunyiwei on 2015/8/20.
 */
public class Test {
    public static void main(String[] args) throws JAXBException {
        final String filename = "C:\\Users\\Lenovo\\Desktop\\test.txt";

        //marshal
        marshal(filename);

        //unmarshal
        unmarshal(filename);
    }

    private static void marshal(String filename) throws JAXBException {
        Json json = new Json();

        JAXBContext context = JAXBContext.newInstance(Json.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(json, new File(filename));
    }

    private static void unmarshal(String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Json.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Json json = (Json)unmarshaller.unmarshal(new File(filename));
        System.out.println(json);
    }
}
