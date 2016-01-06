package com.cmcc.syw.dao;

import com.cmcc.syw.model.User;

public interface UserMapper {
    int delete(String guid);

    int insert(User record);

    User get(String guid);
}