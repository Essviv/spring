package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunyiwei on 2016/2/5.
 */
@Controller
@RequestMapping("jquery")
public class JQueryController {
    @RequestMapping("test")
    public String test(){
        return "jQuery";
    }

    @RequestMapping("bootstrap")
    public String test2(){
        return "bootstrap";
    }
}
