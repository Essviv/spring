package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sunyiwei on 2015/8/25.
 */
@Controller
@RequestMapping("jquery")
public class JSController {
    @RequestMapping("index")
    public String index(){
        return "js";
    }

    @RequestMapping("insert")
    public String insert(){
        return "insert";
    }

    @RequestMapping("ajax")
    public String ajax(){
        return "ajax";
    }

    @RequestMapping("{name}/{gender}")
    public void ajaxRequest(HttpServletResponse resp, @PathVariable String name, @PathVariable String gender, int age) throws IOException {
        resp.getWriter().write("Your name is " + name + ", and your gender is " + gender + ", and you are " + age + " years old.");
    }
}
