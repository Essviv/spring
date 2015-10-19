package com.cmcc.syw.utils;

import com.cmcc.syw.model.Author;
import com.cmcc.syw.model.Student;

import java.util.UUID;

/**
 * Created by sunyiwei on 2015/9/21.
 */
public class CustomBeanFactory {
    public static Student getStudent(){
        return new Student("patrick", 27);
    }

    public Author getAuthor(){
        Author author = new Author();
        author.setName(UUID.randomUUID().toString());
        author.setAge(29);

        return author;
    }
}
