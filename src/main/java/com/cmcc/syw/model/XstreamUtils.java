package com.cmcc.syw.model;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by sunyiwei on 2015/10/23.
 */
public class XstreamUtils {
    public static void main(String[] args) throws FileNotFoundException {
        Article article = new Article();
        article.setId(343L);
        article.setContent("hello world");
        article.setTitle("TITLE");

        Author author = new Author(3434l, "sunyiwei", 27);
        article.setAuthor(author);

        final String filename = "C:\\Users\\Lenovo\\Desktop\\xstream.txt";
        XStream xStream = new XStream();
        xStream.toXML(article, new FileOutputStream(filename));

        Article newArticle = (Article)xStream.fromXML(new File(filename));
        System.out.println(newArticle);
        System.out.println(newArticle.getClass().getClassLoader());
    }
}
