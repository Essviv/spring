package com.cmcc.syw.controller;

import com.cmcc.syw.model.Person;
import com.google.gson.Gson;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sunyiwei on 16/6/23.
 */
@Controller
@RequestMapping("springDoc")
public class SpringDocController {
    @ResponseBody
    @RequestMapping("param")
    public String param(@RequestParam("name") String name,
                        HttpEntity<String> httpEntity,
                        @RequestHeader(value = "Content-Type") String contentType,
                        HttpMethod httpMethod) {
        System.out.println(name);
        System.out.println(httpEntity.getHeaders().get("Content-Type"));
        System.out.println(contentType);
        System.out.println(httpMethod.toString());

        return "OK";
    }

    @RequestMapping("person")
    @ResponseBody
    public Person person(@RequestBody Person person) {
        return person;
    }

    @RequestMapping("resp")
    public HttpEntity<Person> param() {
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));

        webDataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyyMMdd"), false));
    }

    @RequestMapping("initBinder")
    @ResponseBody
    public String param(Date date) {
        return new Gson().toJson(date);
    }
}
