package com.cmcc.syw.utils;

import org.apache.commons.io.FileUtils;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import java.io.File;
import java.io.IOException;

/**
 * markdown4j的实践代码
 * Created by sunyiwei on 2015/10/23.
 */
public class Markdown {
    public static void main(String[] args) throws IOException {
        final String markdownFilename = "C:\\Users\\Lenovo\\Desktop\\markdown.md";
        final String htmlFilename = "C:\\Users\\Lenovo\\Desktop\\markdown.html";

        PegDownProcessor processor = new PegDownProcessor(Extensions.ALL);

        String html = processor.markdownToHtml(FileUtils.readFileToString(new File(markdownFilename)));
        FileUtils.writeStringToFile(new File(htmlFilename), html);

        System.out.println("OK");
    }
}
