package com.cmcc.syw.dao;

import com.cmcc.syw.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int delete(String guid);

    int insert(User record);

    User get(String guid);

    User getByName(String name);

    int batchInsert(@Param("users") List<User> users);
}