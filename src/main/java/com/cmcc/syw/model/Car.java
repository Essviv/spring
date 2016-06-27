package com.cmcc.syw.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.Date;
import java.util.List;

/**
 * Created by sunyiwei on 16/6/12.
 */
@XStreamAlias("CarRequest")
public class Car {
    private String name;
    private double price;

    @XStreamImplicit
    private List<String> suppliers;

    @XStreamConverter(DateConverter.class)
    private Date time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<String> suppliers) {
        this.suppliers = suppliers;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
