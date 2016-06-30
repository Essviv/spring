package com.cmcc.syw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by sunyiwei on 2016/6/29.
 */
@RequestMapping
@Controller
public class Upload {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile multipartFile) {
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getSize());
        System.out.println(multipartFile.getContentType());

        final String outputFilename = "C:\\Users\\Lenovo\\Desktop\\" + multipartFile.getOriginalFilename();
        try {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilename));
            StreamUtils.copy(multipartFile.getInputStream(), outputStream);

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUpload() {
        return "upload";
    }
}
