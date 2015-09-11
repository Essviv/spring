package com.cmcc.syw;

import com.cmcc.syw.model.Author;
import com.cmcc.syw.service.AuthorService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunyiwei on 2015/9/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
public class TestBatch {
    final int count = 20000;

    @Autowired
    private AuthorService service;

    @Test
    @Ignore
    public void testBatchInsert() {
        //sequentially
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            service.insert(get(i));
        }
        System.out.println("Orderly insert: " + (System.currentTimeMillis() - startTime) / 1000L + "s...");

        //batch
        startTime = System.currentTimeMillis();
        List<Author> authorList = new LinkedList<Author>();
        for (int i = 0; i < count; i++) {
            authorList.add(get(i + count));
        }
        service.batchInsert(authorList);
        System.out.println("Batch insert: " + (System.currentTimeMillis() - startTime) / 1000L + "s...");
    }

    @Test
    public void testBatchUpdate() {
        //sequentially
        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < count; i++) {
//            service.update(get(i));
//        }
//        System.out.println("Orderly update: " + (System.currentTimeMillis() - startTime) / 1000L + "s...");

        //batch
        startTime = System.currentTimeMillis();
        List<Author> authorList = new LinkedList<Author>();
        for (int i = 0; i < count; i++) {
            authorList.add(get(i));
        }
        service.batchUpdate(authorList);
        System.out.println("Batch update: " + Double.valueOf(System.currentTimeMillis() - startTime) / 1000L + "s...");
    }

    private Author get(int index) {
        Author author = new Author();
        author.setId(Long.valueOf(index));
        author.setName("Sunyiwei_" + index);
        author.setAge(1000000 + index);

        return author;
    }
}
