package com.cmcc.syw.service.impl;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁的解决方案
 * <p/>
 * Created by sunyiwei on 2016/3/18.
 */
public class DeadLockSol {
    public static void main(String[] args) {
        Friend sunyiwei = new Friend("sunyiwei");
        Friend lisa = new Friend("lisa");

        new Thread(new BowLoop(sunyiwei, lisa)).start();
        new Thread(new BowLoop(lisa, sunyiwei)).start();
    }

    private static class Friend {
        private String name;
        private Lock lock = new ReentrantLock();

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Lock getLock() {
            return lock;
        }

        public void setLock(Lock lock) {
            this.lock = lock;
        }

        public boolean prepareToBow(Friend bow) {
            boolean selfLock = false;
            boolean bowerLock = false;

            try {
                selfLock = lock.tryLock();
                bowerLock = bow.getLock().tryLock();
            } finally {
                if (!(selfLock && bowerLock)) {
                    if (selfLock) {
                        lock.unlock();
                    }

                    if (bowerLock) {
                        bow.getLock().unlock();
                    }
                }
            }

            return selfLock && bowerLock;
        }

        public void bow(Friend bower) {
            if (prepareToBow(bower)) {
                try {
                    System.out.format("%s: %s has bow to me.  %n", bower.getName(), this.name);
                    bower.bowBack(this);
                }finally {
                    lock.unlock();
                    bower.getLock().unlock();
                }
            } else {
                System.out.format("%s: %s started"
                        + " to bow to me, but saw that"
                        + " I was already bowing to"
                        + " him.%n", bower.getName(), this.name);
            }
        }

        public void bowBack(Friend bower) {
            System.out.format("%s: %s has bow back to me. %n", bower.getName(), this.name);
        }
    }

    private static class BowLoop implements Runnable {
        private Friend bower;
        private Friend bowee;

        public BowLoop(Friend bower, Friend bowee) {
            this.bower = bower;
            this.bowee = bowee;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                bower.bow(bowee);
            }
        }
    }
}
