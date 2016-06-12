package com.cmcc.syw.service.impl;

import com.cmcc.syw.dao.UserMapper;
import com.cmcc.syw.model.User;
import com.cmcc.syw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunyiwei on 2016/1/7.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
<<<<<<< f684d74ce6b4c0b92c0fa2cea989d8ceef4903e1
    public User get(String guid) {
        return userMapper.get(guid);
    }

    @Override
    public User getByName(String name) {
        return userMapper.getByName(name);
=======
    public boolean testCreate() {
        for (int i = 0; i < 2; i++) {
            mapper.insert(getUser());
        }

        throw new RuntimeException();
    }

    @Override
    public void noTransactional() {
        testCreate();
    }

    @Override
    @Transactional
    public void firstTrans() {
        try {
            secondTrans();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void secondTrans() {
        throw new RuntimeException();
    }

    @Override
    public boolean insert() {
        final int COUNT = 100;
        List<User> users = new LinkedList<User>();
        for (int i = 0; i < COUNT; i++) {
            users.add(getUser());
        }

        return mapper.batchInsert(users) == COUNT;
    }

    private User getUser() {
        User user = new User();

        user.setFirstName("sunyiwei");
        user.setSecondName("patrick");
        user.setRoleId(33L);
        user.setVersion(34342);

        return user;
>>>>>>> add thread learning code
    }
}
