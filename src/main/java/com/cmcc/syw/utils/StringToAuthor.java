package com.cmcc.syw.utils;

import com.cmcc.syw.model.Author;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sunyiwei on 2015/9/22.
 */
public class StringToAuthor implements Converter<String, Author> {
    @Override
    public Author convert(String source) {
        Author author = new Author();
        author.setName(source);

        return author;
    }
}
