package com.cmcc.syw.markdown;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 尝试使用flexmark解析markdown文档
 *
 * Created by sunyiwei on 2016/12/22.
 */
public class MarkdownParser {
    public static void main(String[] args) throws IOException {
        Parser parser = Parser.builder().build();

        String input = "/Users/sunyiwei/workspace/blogs/doc/memcached.md";
        String mkContent = FileUtils.readFileToString(new File(input), "utf-8");

        String output = "/Users/sunyiwei/workspace/blogs/doc/memcached.html";
        Node node = parser.parse(mkContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String renderContent = renderer.render(node);
        FileUtils.writeStringToFile(new File(output), convert(renderContent), "utf-8");
    }

    private static String convert(String pure){
        StringBuilder builder = new StringBuilder();
        builder.append("<html><head>");
        builder.append("<meta charset=\"utf-8\"/>");
        builder.append("</head><body>");
        builder.append(pure);
        builder.append("</body>");

        return builder.toString();
    }
}
