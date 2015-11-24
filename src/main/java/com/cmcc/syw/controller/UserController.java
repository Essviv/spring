package com.cmcc.syw.controller;

import com.cmcc.syw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunyiwei on 2015/11/23.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping("test")
    public void test(){
        try{
            service.testCreate();
        }catch (Exception e){
            System.err.println("Runtime exception.");
        }
    }

    @RequestMapping("no")
    public void testNoTransactional(){
        try {
            service.noTransactional();
        }catch (Exception e){
            System.err.println("Runtime exception.");
        }
    }

    @RequestMapping("nested")
    public void testNestedTrans(){
        service.firstTrans();
    }
}
