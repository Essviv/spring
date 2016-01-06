package com.cmcc.syw.dao;

import com.cmcc.syw.model.UserRole;

public interface UserRoleMapper {
    int delete(String guid);

    int insert(UserRole record);

    UserRole get(String guid);
}