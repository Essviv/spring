package com.cmcc.syw.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by sunyiwei on 16/6/14.
 */
@XStreamAlias("ManRequest")
public class Man extends Person {
    @XStreamAlias("ManName")
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
