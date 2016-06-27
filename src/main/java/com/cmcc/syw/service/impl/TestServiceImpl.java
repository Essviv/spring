package com.cmcc.syw.service.impl;

import com.cmcc.syw.service.SingletonService;
import com.cmcc.syw.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunyiwei on 2016/6/17.
 */
@Service
public class TestServiceImpl implements TestService, ApplicationContextAware {
    private final int COUNT = 100;

    private ExecutorService executorService = Executors.newFixedThreadPool(COUNT);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void postConstruct() {
        for (int i = 0; i < COUNT; i++) {
            executorService.submit(new LongTimeRunner(applicationContext));
        }
    }

    @PreDestroy
    public void preDestroy() {
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void service() {

    }

    private class LongTimeRunner implements Runnable {
        private ApplicationContext applicationContext;

        public LongTimeRunner(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        @Override
        public void run() {
            while (true) {
                SingletonService singletonService = applicationContext.getBean("singletonService", SingletonService.class);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
