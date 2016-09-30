package com.cmcc.syw.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunyiwei on 16/9/30.
 */
@XmlRootElement(name = "Countries")
public class Countries {
    List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    @XmlElement(name = "Country")
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
