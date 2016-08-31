package com.cmcc.syw.service.impl;

import com.cmcc.syw.dao.AccountMapper;
import com.cmcc.syw.model.Account;
import com.cmcc.syw.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sunyiwei on 2016/8/31.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public boolean insert(Account account) {
        return validate(account) && accountMapper.insert(account) == 1;
    }

    @Override
    public Account get(Long id) {
        return id == null ? null : accountMapper.get(id);
    }

    @Override
    public boolean updateDelta(Long id, int delta) {
        return id != null && accountMapper.updateDelta(id, delta) == 1;
    }

    @Override
    public boolean update(Long id, int delta) {
        Account account = accountMapper.get(id);
        return id != null
                && account != null
                && accountMapper.update(id, account.getCount() + delta, account.getVersion()) == 1;
    }

    private boolean validate(Account account) {
        return account != null
                && account.getCount() != null
                && account.getVersion() != null;
    }
}
