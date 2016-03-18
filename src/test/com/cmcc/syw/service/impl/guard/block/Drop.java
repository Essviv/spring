package com.cmcc.syw.service.impl.guard.block;

/**
 * Created by sunyiwei on 2016/3/17.
 */
public class Drop {
    private boolean empty = true;
    private String message;

    public synchronized String take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //toggle the status
        empty = true;

        //notify all other threads
        notifyAll();

        return message;
    }

    public synchronized void put(String message){
        while(!empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //toggle the status
        empty = false;

        this.message = message;

        //notify all other threads
        notifyAll();
    }
}
