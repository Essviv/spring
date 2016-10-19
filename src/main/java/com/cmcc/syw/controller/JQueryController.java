package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunyiwei on 2016/2/5.
 */
@Controller
@RequestMapping("jquery")
public class JQueryController {
    @RequestMapping("test")
    public String test() {
        return "jQuery";
    }

    @RequestMapping("test3")
    @ResponseBody
    public String test3(HttpServletRequest request) {
        String req = request.getParameter("test");
        return req;
    }

    @RequestMapping("bootstrap")
    public String test2() {
        return "bootstrap";
    }
}
