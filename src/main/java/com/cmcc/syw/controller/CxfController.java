package com.cmcc.syw.controller;

import com.cmcc.syw.cxf.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunyiwei on 16/9/29.
 */
@Controller
@RequestMapping("wstest")
public class CxfController {
    @Autowired
    private HelloWorld helloWorld;

    @RequestMapping("index/{name}")
    public String cxf(@PathVariable String name) {
        System.out.println(helloWorld.sayHi(name));
        return "welcome";
    }
}
