package com.cmcc.syw.controller;

import com.google.gson.Gson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunyiwei on 2015/11/23.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private static String randStr(int length) {
        StringBuilder sb = new StringBuilder();

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }

        return sb.toString();
    }

    @RequestMapping
    public String test(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(new Gson().toJson(cookie));
        }

        for (int i = 0; i < 21; i++) {
            response.addCookie(buildCookie());
        }

        return "welcome";
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(new Gson().toJson(cookie));
        }

        return "welcome";
    }

    private Cookie buildCookie() {
        Cookie cookie = new Cookie(randStr(6), randStr(6));
        cookie.setComment(randStr(12));
//        cookie.setSecure(true);
        cookie.setPath("/spring/users/");
        cookie.setMaxAge(120);

        return cookie;
    }
}
