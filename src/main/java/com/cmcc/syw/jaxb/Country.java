package com.cmcc.syw.jaxb;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by sunyiwei on 16/9/30.
 */
@XmlRootElement(name = "Country")
@XmlType(propOrder = {"capital", "name", "population", "foundation", "importance"})
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Country {
    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Foundation")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date foundation;

    @XmlElement(name = "Capital")
    private String capital;

    @XmlAttribute(name = "Importance")
    private boolean importance;

    @XmlElement(name = "Population")
    private int population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundation() {
        return foundation;
    }

    public void setFoundation(Date foundation) {
        this.foundation = foundation;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public boolean isImportance() {
        return importance;
    }

    public void setImportance(boolean importance) {
        this.importance = importance;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
