package com.cmcc.syw.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by sunyiwei on 2015/12/13.
 */
@XStreamAlias("Request")
public class QueryReq {
    @XStreamAlias("Addr")
    private String addr;

    @XStreamAlias("FirstName")
    private String firstName;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
