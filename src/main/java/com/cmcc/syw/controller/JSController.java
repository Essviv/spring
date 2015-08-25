package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
