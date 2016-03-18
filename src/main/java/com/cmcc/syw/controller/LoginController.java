package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunyiwei on 2016/1/6.
 */
@RequestMapping("/")
@Controller
public class LoginController {
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("logout")
    public String logout(){
        return "logout";
    }
}
