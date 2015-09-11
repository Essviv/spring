package com.cmcc.syw.service;

import com.cmcc.syw.model.Author;

import java.util.List;

/**
 * Created by sunyiwei on 2015/9/9.
 */
public interface AuthorService {
    int insert(Author author);

    int batchInsert(List<Author> authors);

    int update(Author author);

    int batchUpdate(List<Author> authors);
}
