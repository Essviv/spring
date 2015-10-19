package com.cmcc.syw.utils;

import com.cmcc.syw.model.Author;

import java.beans.PropertyEditorSupport;

/**
 * Created by sunyiwei on 2015/9/22.
 */
public class AuthorPropertyEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        Author author = (Author) getValue();
        return author.toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Author author = new Author();
        author.setName(text);

        setValue(author);
    }
}
