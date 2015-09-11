package com.cmcc.syw.concurrency;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunyiwei on 2015/9/11.
 */
public class TestMultiLog {
    public static void main(String[] args) throws InterruptedException, IOException {
        final String filePath = "C:\\Users\\Lenovo\\Desktop\\test.txt";
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));

        final int count = 100;

        List<Callable<String>> tasks = new LinkedList<Callable<String>>();
        for (int i = 0; i < count; i++) {
            tasks.add(new Callable<String>() {
                @Override
                public String call() throws IOException {
                    int index = 0;
                    while (index++ < 10) {
                        writer.write(Thread.currentThread().getName() + ": 这是日志输出...");
                        writer.newLine();
                    }

                    return "";
                }
            });
        }

        ExecutorService service = Executors.newFixedThreadPool(count);
        service.invokeAll(tasks);

        service.shutdown();

        writer.write("Complete...");
        writer.newLine();

        writer.flush();
        writer.close();
    }
}
