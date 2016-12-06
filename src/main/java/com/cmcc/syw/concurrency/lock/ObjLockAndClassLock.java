package com.cmcc.syw.concurrency.lock;

import java.io.*;
import java.util.Formatter;

/**
 * demo to prove that obj lock and its class lock are two different lock objs
 * Created by sunyiwei on 16/4/17.
 */
public class ObjLockAndClassLock {

    public static void main(String[] args) {
        final ObjLockAndClassLock local = new ObjLockAndClassLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    local.testObjLock("objLockFile1.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "Obj-Lock-Thread-1").start();

        //this thread would never get local's lock, thus won't print anything.
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    local.testObjLock("objLockFile2.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "Obj-Lock-Thread-2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjLockAndClassLock.testClassLock("ClassLockFile.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "Class-Lock-Thread").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    local.testNoLock("NoLockFile.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "No-Lock-Thread-1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    local.testNoLock("NoLockFile.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "No-Lock-Thread-2").start();
    }

    //class lock has to be gained before entering into this method
    private synchronized static void testClassLock(String filename) throws IOException {
        String currentThreadName = Thread.currentThread().getName();
        Formatter formatter = new Formatter();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));

        while (true) {
            String string = formatter.format("%s has retrieved class lock. %n", currentThreadName).toString();
            bw.write(string);
            bw.newLine();
        }
    }

    //obj lock has to be gained before entering into this method
    public synchronized void testObjLock(String filename) throws IOException {
        String currentThreadName = Thread.currentThread().getName();
        Formatter formatter = new Formatter();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));

        while (true) {
            String string = formatter.format("%s has retrieved obj lock. %n", currentThreadName).toString();
            bw.write(string);
            bw.newLine();
        }
    }

    //no lock is required
    public void testNoLock(String filename) throws IOException {
        String currentThreadName = Thread.currentThread().getName();
        Formatter formatter = new Formatter();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));

        while (true) {
            String string = formatter.format("%s has entering into the testNoLock method. %n",
                    currentThreadName).toString();

            bw.write(string);
            bw.newLine();
        }
    }
}
