package com.cmcc.syw.dao;

import com.cmcc.syw.model.Author;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorMapper {
    int batchInsert(@Param("authors") List<Author> authors);

    int insert(Author record);

    int batchUpdate(@Param("authors") List<Author> authors);

    int update(Author author);
}