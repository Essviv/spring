package com.cmcc.syw.service.impl.guard.block;

/**
 * Created by sunyiwei on 2016/3/17.
 */
public class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        while(true){
            String message = drop.take();
            if(message.equals("DONE")){
                break;
            }

            System.out.format("%s: %s. %n", Thread.currentThread().getName(), message);
        }
    }
}
