package com.cmcc.syw.controller;

import com.cmcc.syw.model.Account;
import com.cmcc.syw.service.AccountService;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用于测试spring-test模块的controller
 * <p/>
 * Created by sunyiwei on 2016/10/20.
 */
@Controller
@RequestMapping("/spring-test/")
public class SpringTestController {
    @Autowired
    AccountService accountService;

    @RequestMapping("test1")
    @ResponseBody
    public String test1(String name) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", name);

        return new Gson().toJson(map);
    }

    @RequestMapping("test2")
    public void test2(@RequestParam("name") String name, HttpServletResponse response) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", name);

        try {
            IOUtils.copy(new StringReader(new Gson().toJson(map)), response.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("test3")
    @ResponseBody
    public boolean test3(Account account) {
        return accountService.insert(account);
    }
}
