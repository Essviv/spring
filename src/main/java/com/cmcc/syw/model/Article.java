package com.cmcc.syw.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Article {
    private Long id;

    private Author author;

    private String title;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @PostConstruct
    public void init(){
        System.out.println("Init...");
    }

    @PreDestroy
    public void destruct(){
        System.out.println("Destruct...");
    }
}