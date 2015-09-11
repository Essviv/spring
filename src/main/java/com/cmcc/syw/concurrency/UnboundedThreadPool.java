package com.cmcc.syw.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NEVER TRY TO RUN THIS CLASS!!!!!!!!
 * Created by sunyiwei on 2015/9/11.
 */
public class UnboundedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        //here starts the disaster
        while(true){
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hia~hia~hia~I'm evil~");
                }
            });
        }
    }
}
