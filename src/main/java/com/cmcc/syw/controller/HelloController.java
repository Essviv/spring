package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(value = "hi", method = RequestMethod.GET)
	public String printWelcome() {
		return "welcome";
	}

    @RequestMapping(value="{name}", method = RequestMethod.GET)
    public String index(ModelMap modelMap, @PathVariable String name){
        modelMap.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("world")
    public String index(){
        return "dom";
    }

    @RequestMapping("/ajax/{name}")
    public void getName(HttpServletResponse response, @PathVariable String name) throws IOException {
        response.getWriter().write(name);
    }
}