package com.cmcc.syw.service;

import com.cmcc.syw.model.Account;

/**
 * Created by sunyiwei on 2016/8/31.
 */
public interface AccountService {
    boolean insert(Account account);

    Account get(Long id);

    boolean update(Long id, int delta);

    boolean updateDelta(Long id, int delta);
}
