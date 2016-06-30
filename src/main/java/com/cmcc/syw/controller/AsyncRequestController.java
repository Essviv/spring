package com.cmcc.syw.controller;

import com.cmcc.syw.model.Car;
import com.cmcc.syw.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by sunyiwei on 2016/6/27.
 */
@RequestMapping("/async")
@Controller
public class AsyncRequestController {
    @RequestMapping("async")
    @ResponseBody
    public Callable<Person> async() {
        return () -> {
            Thread.sleep(2000);

            return buildPerson();
        };
    }

    @RequestMapping("sync")
    @ResponseBody
    public Person sync() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return buildPerson();
    }


    @RequestMapping("queryDefered")
    @ResponseBody
    public DeferredResult<Person> asynDeferredResult() {
        DeferredResult<Person> deferredResult = new DeferredResult<>(5000L, buildPerson("Essviv"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                deferredResult.setResult(buildPerson());
            }
        }).start();

        return deferredResult;
    }


    private Person buildPerson() {
        return buildPerson("sunyiwei");
    }

    private Person buildPerson(String name) {
        Person person = new Person();
        person.setName(name);

        Car car = new Car();
        car.setName("BMW");
        person.setCar(car);

        person.setBirthday(new Date());

        return person;
    }
}
