package com.cmcc.syw.service.impl;

/**
 * Created by sunyiwei on 2016/3/17.
 */
public class Deadlock {
    public static void main(String[] args) {
        final Friend sunyiwei = new Friend("sunyiwei");
        final Friend lisa = new Friend("lisa");

        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                sunyiwei.bow(lisa);
            }
        }, "sunyiwei");
        A.start();

//        // solution of deadlock
//        try {
//            A.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                lisa.bow(sunyiwei);
            }
        }, "lisa").start();
    }

    private static class Friend {
        private String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bow to me. %n", bower.getName(), this.name);
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bow back to me. %n", this.name, bower.getName());
        }
    }
}
