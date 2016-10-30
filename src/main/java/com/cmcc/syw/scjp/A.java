package com.cmcc.syw.scjp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by sunyiwei on 2016/10/25.
 */
@XStreamAlias("A")
public class A extends SuperA {
    @XStreamAlias("order")
    private String order;

    @XStreamAlias("a")
    private String a;



    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
