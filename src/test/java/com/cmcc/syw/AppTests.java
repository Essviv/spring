package com.cmcc.syw;

import com.cmcc.syw.model.Article;
import com.cmcc.syw.model.Author;
import com.cmcc.syw.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
public class AppTests {
    @Autowired
    Student patrick;

    @Autowired
    Author syw;

    @Autowired
    Article article;

    @Test
    public void test() {
        System.out.println(article);
    }
}
