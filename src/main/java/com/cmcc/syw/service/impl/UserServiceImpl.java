package com.cmcc.syw.service.impl;

import com.cmcc.syw.dao.UserMapper;
import com.cmcc.syw.model.User;
import com.cmcc.syw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sunyiwei on 2016/1/7.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User get(String guid) {
        return userMapper.get(guid);
    }

    @Override
    public User getByName(String name) {
        return userMapper.getByName(name);
    }
}
