package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunyiwei on 16/7/2.
 */
@RequestMapping
@Controller
public class ResourceHandler {
   @RequestMapping("/resource")
   public String resource(){
      return "resource";
   }
}
