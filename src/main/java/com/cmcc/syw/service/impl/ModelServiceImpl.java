package com.cmcc.syw.service.impl;

import com.google.gson.Gson;

import com.cmcc.syw.model.Model;
import com.cmcc.syw.model.Models;
import com.cmcc.syw.service.ModelService;

import java.util.Random;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by sunyiwei on 16/9/29.
 */
public class ModelServiceImpl implements ModelService {
    private Models models = null;

    public ModelServiceImpl() {
        init();
    }

    private static String randStr(int length) {
        StringBuilder sb = new StringBuilder();

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }

        return sb.toString();
    }

    //随机产生10个model
    private void init() {
        final int COUNT = 10;
        for (int i = 0; i < COUNT; i++) {
            Model model = new Model();
            model.setAge(i + 15);
            model.setGender(i % 2 == 0 ? "M" : "F");
            model.setName(randStr(16));

            models.getModels().add(model);
        }

        System.out.println(new Gson().toJson(models));
    }

    @Override
    public Model get(@PathParam("name") String name) {
        for (Model model : models.getModels()) {
            if (model.getName().equals(name)) {
                return model;
            }
        }

        return null;
    }

    @Override
    public Response create(Model model) {
        models.getModels().add(model);
        Response.ResponseBuilder builder = Response.status(Response.Status.OK);
        builder.header("Content-Type", "application/xml");
        return builder.build();
    }
}
