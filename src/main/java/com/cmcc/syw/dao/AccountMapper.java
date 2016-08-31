package com.cmcc.syw.dao;

import com.cmcc.syw.model.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    Account get(Long id);

    int insert(Account record);

    int update(@Param("id") Long id, @Param("newCount") int newCount, @Param("oldVersion") int oldVersion);

    int updateDelta(@Param("id") Long id, @Param("delta") int delta);
}