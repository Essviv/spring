package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunyiwei on 2015/11/23.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @RequestMapping
    public String test(){
        return "welcome";
    }
}
