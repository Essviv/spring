package com.cmcc.syw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sunyiwei on 16/6/29.
 */
@RequestMapping
@Controller
public class ExceptionHandlerController {
    @RequestMapping("/exception")
    public String throwException() {
        throw new RuntimeException();
    }

    @RequestMapping("/responseStatus")
    public String throwException(String content) {
        throw new CustomException();
    }

    @RequestMapping("/standardException")
    public String standardException() throws BindException {
        throw new BindException(new Object(), "");
    }

//    @ExceptionHandler(value = RuntimeException.class)
//    @ResponseBody
//    public String handleException(HttpServletResponse response) {
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        return "Bad request.";
//    }

    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "unsupported media type")
    private class CustomException extends RuntimeException {

    }
}
