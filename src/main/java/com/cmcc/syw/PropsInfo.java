package com.cmcc.syw;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunyiwei on 2017/2/8.
 */
public class PropsInfo {
    private String id;
    private String name;

    public static void main(String[] args) {
        List<PropsInfo> infos = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            PropsInfo info = new PropsInfo();
            info.setId(String.valueOf(i));
            info.setName("Name_" + String.valueOf(i));

            infos.add(info);
        }

        System.out.println(new Gson().toJson(infos));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
