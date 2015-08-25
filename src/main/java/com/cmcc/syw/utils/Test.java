package com.cmcc.syw.utils;

import com.cmcc.syw.dao.ArticleMapper;
import com.cmcc.syw.dao.UserMapper;
import com.cmcc.syw.model.Article;
import com.cmcc.syw.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunyiwei on 2015/8/20.
 */
public class Test {
    private static SqlSessionFactory ssf;
    private static Reader reader;

    static{
        try {
            reader = Resources.getResourceAsReader("conf/configuration.xml");
            ssf = new SqlSessionFactoryBuilder().build(reader);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static SqlSessionFactory getFactory(){
        return ssf;
    }

    public static void main(String[] args) {
        SqlSession session = ssf.openSession();

        try {
            ArticleMapper mapper = session.getMapper(ArticleMapper.class);
            List<Article> articles = mapper.list();
            for (Article article : articles) {
                System.out.println(article);
            }
        } finally {
            session.close();
        }
    }

    private static List<User> getList(){
        List<User> users = new ArrayList<User>();
        final int count = 100;

        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setAddress("HLJ_" + i);
            user.setAge(10 + i);
            user.setName("user_" + i);

            users.add(user);
        }

        return users;
    }
}
