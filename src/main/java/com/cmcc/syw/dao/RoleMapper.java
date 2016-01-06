package com.cmcc.syw.dao;

import com.cmcc.syw.model.Role;

public interface RoleMapper {
    int delete(String guid);

    int insert(Role record);

    Role get(String guid);
}