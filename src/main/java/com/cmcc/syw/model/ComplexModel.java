package com.cmcc.syw.model;

import com.cmcc.syw.annotations.SerializerAnnotation;

import java.util.List;

/**
 * Created by sunyiwei on 2016/11/7.
 */
@SerializerAnnotation("AnnotatedModel")
public class ComplexModel {
    private User user;
    private String name;
    private List<Model> models;
    private String[] strArray;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public String[] getStrArray() {
        return strArray;
    }

    public void setStrArray(String[] strArray) {
        this.strArray = strArray;
    }
}
