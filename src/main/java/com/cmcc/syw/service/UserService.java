package com.cmcc.syw.service;

import com.cmcc.syw.model.User;

import java.util.List;

/**
 * Created by sunyiwei on 2015/11/23.
 */
public interface UserService {
    User get(String guid);

    User getByName(String name);

    void secondTrans();

    boolean insert();
}
