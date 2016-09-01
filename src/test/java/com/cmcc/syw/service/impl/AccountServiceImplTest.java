package com.cmcc.syw.service.impl;

import com.cmcc.syw.model.Account;
import com.cmcc.syw.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by sunyiwei on 2016/8/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
public class AccountServiceImplTest {
    @Autowired
    AccountService accountService;

    @Test
    public void testUpdate() {
        try {
            abstractOp(new Operator() {
                @Override
                public boolean operate(Long id, int delta) {
                    if (!accountService.update(id, delta)) {
                        System.err.println("操作账户失败[Update], 金额为" + delta);
                        return true;
                    }

                    return false;
                }
            });
        } catch (Exception e) {
            System.err.println("Error msg: " + e);
        }
    }

    @Test
    public void testUpdateDelta() {
        try {
            abstractOp(new Operator() {
                @Override
                public boolean operate(Long id, int delta) {
                    if (!accountService.updateDelta(id, delta)) {
                        System.err.println("[UpdateDelta]操作账户失败, 金额为" + delta);
                        return true;
                    }

                    return false;
                }
            });
        } catch (Exception e) {
            System.err.println("Error msg: " + e);
        }
    }

    private void abstractOp(Operator operator) throws InterruptedException {
        Account account = build();
        assertTrue(accountService.insert(account));

        final Long id = account.getId();
        final int COUNT = 1000;
        final int totalCount = 10000;
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        final int subBlockSize = totalCount / COUNT;
        List<Integer> list = randInt(totalCount);
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();

        for (int i = 0; i < COUNT; i++) {
            final int index = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    int begin = index * subBlockSize;
                    int end = begin + subBlockSize;
                    for (int j = begin; j < end; j++) {
                        if(operator.operate(id, list.get(j))) {
                            map.put(j, list.get(j));
                        }
                    }

                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdownNow();

        account = accountService.get(id);
        System.out.println(account.getCount());
        System.out.println(account.getVersion());
        System.out.println(list.stream().mapToInt(Integer::intValue).sum());
        System.out.println(new LinkedList<>(map.values()).stream().mapToInt(Integer::intValue).sum());
    }

    private List<Integer> randInt(int totalCount) {
        Random random = new Random();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < totalCount; i++) {
            list.add(random.nextInt(100) - 50); // -50 ~ 50
        }

        return list;
    }

    private Account build() {
        Account account = new Account();
        account.setVersion(0);
        account.setCount(10000000);

        return account;
    }

    private interface Operator {
        boolean operate(Long id, int delta);
    }
}