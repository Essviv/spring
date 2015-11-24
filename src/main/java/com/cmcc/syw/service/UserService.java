package com.cmcc.syw.service;

/**
 * Created by sunyiwei on 2015/11/23.
 */
public interface UserService {
    boolean testCreate();

    void noTransactional();

    void firstTrans();

    void secondTrans();
}
