package com.cmcc.syw.controller;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunyiwei on 16/7/2.
 */
@RequestMapping
@Controller
public class CacheController {
    @RequestMapping("/cache")
    @ResponseBody
    public String common(HttpServletRequest httpServletRequest, HttpServletResponse response) throws ParseException {
        String ifNotModifySince = httpServletRequest.getHeader("If-Modified-Since");

        if (StringUtils.isBlank(ifNotModifySince)
                || new DateTime().minusSeconds(3).isAfter(fromStr(ifNotModifySince))){
            response.setHeader("Cache-Control", "public");
//            response.setHeader("Last-Modified", new Date().toString());
            response.setHeader("Expires", new DateTime().plusSeconds(10).toString());
            return "Out of date";
        }

        response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        return "Not modified";
    }

    private DateTime fromStr(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = sdf.parse(dateStr);

        return new DateTime(d);
    }
}

