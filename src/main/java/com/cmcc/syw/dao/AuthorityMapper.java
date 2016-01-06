package com.cmcc.syw.dao;

import com.cmcc.syw.model.Authority;

public interface AuthorityMapper {
    int delete(String guid);

    int insert(Authority record);

    Authority get(String guid);
}