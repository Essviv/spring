package com.cmcc.syw.dao;

import com.cmcc.syw.model.RoleAuthority;

public interface RoleAuthorityMapper {
    int delete(String guid);

    int insert(RoleAuthority record);

    RoleAuthority get(String guid);
}