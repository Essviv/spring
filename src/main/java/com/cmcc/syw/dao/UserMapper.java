package com.cmcc.syw.dao;

import com.cmcc.syw.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User get(Long id);

    List<User> list();

    int delete(Long id);

    int update(@Param("id") Long id, @Param("address") String address);

    int insert(User user);
}