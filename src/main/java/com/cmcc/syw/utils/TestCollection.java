package com.cmcc.syw.utils;

import com.cmcc.syw.model.User;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.owasp.encoder.Encode;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.IntFunction;

/**
 * Created by sunyiwei on 15-12-21.
 */
public class TestCollection {
    public static void main(String[] args) {
//        String[] strs = asList();
//
//        String[] subStrs = copyOf(strs);
//
//        testSort(subStrs);
//
//        binarySearch(subStrs, strs[0]);
//
//        fill();

//        testHashSet();
//
//        testLinkedHashSet();
//
//        testTreeSet();

//        testQueue();
//
//        testDeque();

        //testLinkedHashMap();
//       testCollections();

//        testJavaEncoder();
        testShiro();
    }

    private static String[] asList(final int count) {
        String[] strs = new String[count];
        Arrays.setAll(strs, new IntFunction<String>() {
            @Override
            public String apply(int value) {
                return randStr();
            }
        });

        List<String> strList = Arrays.asList(strs);
//        System.out.println(strList);
        return strs;
    }

    private static String[] copyOf(String[] strs) {
        String[] subStrs = Arrays.copyOf(strs, 20);
        List<String> subStrList = Arrays.asList(subStrs);
        System.out.println(subStrList);
        return subStrs;
    }

    private static void binarySearch(String[] subStrs, String findStr) {
        System.out.println(findStr + " is in the index of "
                + Arrays.binarySearch(subStrs, findStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }));
    }

    private static void fill() {
        Integer[] ints = new Integer[10];
        Arrays.fill(ints, 10);

        List<Integer> intList = Arrays.asList(ints);
        System.out.println(intList);
    }

    private static void testSort(String[] subStrs) {
        Arrays.sort(subStrs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals(o2)) {
                    return 0;
                }

                return o1.compareTo(o2);
            }
        });

        List<String> subStrList = Arrays.asList(subStrs);
        System.out.println(subStrList);
    }


    private static String randStr() {
        final int count = 5;
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
    }

    private static void testHashSet() {
        String[] strs = asList(10);
        List<String> strList = Arrays.asList(strs);
        HashSet<String> set = new HashSet<String>(strList);

        System.out.println(Arrays.asList(strs));
        System.out.println(set);
    }

    private static void testLinkedHashSet() {
        String[] strs = asList(10);
        List<String> strList = Arrays.asList(strs);
        LinkedHashSet<String> set = new LinkedHashSet<String>(strList);

        System.out.println(Arrays.asList(strs));
        System.out.println(set);
    }

    private static void testTreeSet() {
        String[] strs = asList(10);
        List<String> strList = Arrays.asList(strs);
        TreeSet<String> set = new TreeSet<String>(strList);

        System.out.println(Arrays.asList(strs));
        System.out.println(set);
    }

    private static void testQueue() {
        final int count = 10;
        Queue<String> queue = new ArrayBlockingQueue<String>(count);

        for (int i = 0; i < count; i++) {
            queue.add(randStr());
        }

        queue.offer("sunyiwei");
        System.out.println(queue);

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.peek());
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.poll());
        }

        System.out.println(queue.size());
    }

    private static void testDeque() {
        Deque<String> deque = new ArrayDeque<String>();
        final int count = 10;

        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                deque.addFirst(randStr());
            } else {
                deque.addLast(randStr());
            }
        }

        System.out.println(deque);
        System.out.println(deque.size());

        Deque<String> blockingDeque = new LinkedBlockingDeque<String>();
        blockingDeque.offer("sunyiwei");
    }

    private static void testLinkedHashMap() {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
        lhm.put("name", "sunyiwei");
        System.out.println(lhm);
    }

    private static void testCollections() {
        List<Integer> strs = new ArrayList<Integer>();
        final int count = 10;

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            strs.add(Math.abs(random.nextInt(count)));
        }

        System.out.println(strs);

        Collections.shuffle(strs);

        System.out.println(strs);
    }

    private static void testJavaEncoder(){
        User user = new User();
        user.setFirstName("sunyiwei");
        user.setSecondName("Patrick");
        user.setVersion(1);

        System.out.println("<textarea>"+ Encode.forHtml(new Gson().toJson(user))+"</textarea>");
    }

    private static void testShiro(){
        DefaultSecurityManager sm = new DefaultSecurityManager();
        SecurityUtils.setSecurityManager(sm);

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("name", "sunyiwei");

        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken("sunyiwei", "syw514912821");
            token.setRememberMe(true);
//            subject.login(token);
        }

        System.out.println(subject.isRemembered());
        System.out.println(subject.isAuthenticated());
        System.out.println("User[" + subject.getPrincipal() + "]logged in successfully.");
        if(subject.hasRole("admin")){
            System.out.println("May the god be with you!");
        }else{
            System.out.println("Hello, anonymous.");
        }

        if(subject.isPermitted("account:read")){
            System.out.println("Plz read your account, my lord.");
        }else{
            System.out.println("Sorry, you are not authorized to do this.");
        }

        subject.logout();
    }
}
