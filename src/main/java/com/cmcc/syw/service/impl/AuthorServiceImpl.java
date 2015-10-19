package com.cmcc.syw.service.impl;

import com.cmcc.syw.dao.AuthorMapper;
import com.cmcc.syw.model.Author;
import com.cmcc.syw.service.AuthorService;
import com.cmcc.syw.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunyiwei on 2015/9/9.
 */
@Service("authorService")
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorMapper mapper;

    @Override
    public int insert(Author author) {
        return mapper.insert(author);
    }

    @Override
    public int batchInsert(List<Author> authors) {
        return mapper.batchInsert(authors);
    }

    @Override
    public int update(Author author) {
        return mapper.update(author);
    }

    @Override
    public int batchUpdate(List<Author> authors) {
        int count = authors.size();

        int startIndex = 0;
        int offset = 5000;

        int updateCount = 0;
        while(startIndex + offset < count){
            updateCount += mapper.batchUpdate(authors.subList(startIndex, startIndex + offset));
            startIndex += offset;
        }

        updateCount +=mapper.batchUpdate(authors.subList(startIndex, count));

        assert(updateCount == count);

        return updateCount;
    }
}
