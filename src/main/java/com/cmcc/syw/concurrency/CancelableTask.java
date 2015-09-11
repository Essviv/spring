package com.cmcc.syw.concurrency;

import com.cmcc.syw.utils.Utils;
import org.springframework.scheduling.config.Task;

import javax.xml.transform.Source;

/**
 * Created by sunyiwei on 2015/9/10.
 */
public class CancelableTask implements Runnable{
    private boolean isCannelled;

    @Override
    public void run() {
        int index = 0;
        while (!isCannelled){
            System.out.println(Thread.currentThread().getName() + ": " +Utils.fibonaci(index++));
        }
    }

    public void cancel(){
        isCannelled = true;
    }

    public static void main(String[] args) {
        CancelableTask task = new CancelableTask();
        Thread thread = new Thread(task);
        thread.start();

        try {
            Thread.sleep(2000);
            task.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ": Task has been cancel...");
    }
}
