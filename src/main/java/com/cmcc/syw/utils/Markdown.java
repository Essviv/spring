package com.cmcc.syw.utils;

import org.apache.commons.io.FileUtils;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import java.io.File;
import java.io.IOException;

/**
 * markdown4jµÄÊµ¼ù´úÂë
 * Created by sunyiwei on 2015/10/23.
 */
public class Markdown {
    public static void main(String[] args) throws IOException {
        final String markdownFilename = "/markdown.txt";
        final String htmlFilename = "C:\\Users\\Lenovo\\Desktop\\markdown.html";

        File file = new File(Markdown.class.getResource(markdownFilename).getFile());
        String html = new PegDownProcessor(Extensions.ALL).markdownToHtml(FileUtils.readFileToString(file));
        FileUtils.writeStringToFile(new File(htmlFilename), html);

        System.out.println("OK");
    }
}
