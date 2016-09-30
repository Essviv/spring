package com.cmcc.syw.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunyiwei on 16/9/29.
 */
@XmlRootElement(name = "Models")
public class Models {
    @XmlElement(name = "Model")
    private List<Model> models;

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
