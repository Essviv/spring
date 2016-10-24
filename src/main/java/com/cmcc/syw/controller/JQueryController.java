package com.cmcc.syw.controller;

import com.google.gson.Gson;

import org.apache.commons.codec.Charsets;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("test4")
    public void test4(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("name", req.getParameter("name"));
        params.put("value", req.getParameter("value"));

        try {
            StreamUtils.copy(new Gson().toJson(params), Charsets.UTF_8, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("bootstrap")
    public String test2() {
        return "bootstrap";
    }
}
