package com.cmcc.syw.jaxb;

import com.google.gson.Gson;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Created by sunyiwei on 16/9/30.
 */
public class Main {
    public static void main(String[] args) throws JAXBException {
        final String OUTPUT1 = "/Users/sunyiwei/Desktop/output1.xml";
        final String OUTPUT2 = "/Users/sunyiwei/Desktop/output2.xml";


        toXML(build(), OUTPUT1);

        Countries coutries = new Countries();
        coutries.setCountries(buildCountries());
        toXML(coutries, OUTPUT2);

        fromXml(OUTPUT1);
        fromXml2(OUTPUT2);
    }

    private static void fromXml(String input) throws JAXBException {
        Unmarshaller unmarshaller = buildUnmarshaller(Country.class);
        Country country = (Country) unmarshaller.unmarshal(new File(input));
        System.out.println(new Gson().toJson(country));
    }

    private static void fromXml2(String input) throws JAXBException {
        Unmarshaller unmarshaller = buildUnmarshaller(Countries.class);
        Countries countries = (Countries) unmarshaller.unmarshal(new File(input));
        System.out.println(new Gson().toJson(countries));
    }

    private static void toXML(Country country, String output) throws JAXBException {
        Marshaller marshaller = buildMarshaller(Country.class);
        marshaller.marshal(country, System.out);
        marshaller.marshal(country, new File(output));
    }

    private static void toXML(Countries countries, String output) throws JAXBException {
        Marshaller marshaller = buildMarshaller(Countries.class);
        marshaller.marshal(countries, System.out);
        marshaller.marshal(countries, new File(output));
    }

    private static Marshaller buildMarshaller(Class clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        return marshaller;
    }

    private static Unmarshaller buildUnmarshaller(Class clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        return context.createUnmarshaller();
    }

    private static List<Country> buildCountries() {
        final int COUNT = 10;
        List<Country> countries = new LinkedList<>();
        for (int i = 0; i < COUNT; i++) {
            countries.add(build());
        }

        return countries;
    }

    private static Country build() {
        Country country = new Country();
        country.setName(randStr(16));
        country.setCapital(randStr(16));
        country.setFoundation(new Date());
        country.setPopulation(234 * 1000);
        country.setImportance(true);

        return country;
    }

    private static String randStr(int length) {
        StringBuilder sb = new StringBuilder();

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }

        return sb.toString();
    }
}
