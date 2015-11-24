package com.cmcc.syw.service.impl;

import com.cmcc.syw.dao.UserMapper;
import com.cmcc.syw.model.User;
import com.cmcc.syw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sunyiwei on 2015/11/23.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public boolean testCreate() {
        for (int i = 0; i < 2; i++) {
            mapper.insert(getUser());
        }

        throw new RuntimeException();
    }

    @Override
    public void noTransactional(){
        testCreate();
    }

    @Override
    @Transactional
    public  void firstTrans(){
        try{
            secondTrans();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void secondTrans(){
        throw new RuntimeException();
    }

    private User getUser(){
        User user = new User();

        user.setFirstName("sunyiwei");
        user.setSecondName("patrick");
        user.setRoleId(33L);
        user.setVersion(34342);

        return user;
    }
}
