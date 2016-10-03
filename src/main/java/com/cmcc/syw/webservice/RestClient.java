package com.cmcc.syw.webservice;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.MediaType;

/**
 * Created by sunyiwei on 16/10/2.
 */
public class RestClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Client client = ClientBuilder.newClient();

        //query sayHello
        querySayHello(client);

        //query getUsers
        queryUsers(client);

        client.close();
    }

    private static void queryUsers(Client client) {
        String resp = client.target("http://localhost:8080/spring/ws/users")
                .request(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(resp);
    }

    private static void querySayHello(Client client) throws ExecutionException, InterruptedException {
        String name = randStr(16);
        System.out.println("Name: " + name);

        Future<String> resp = client.target("http://localhost:8080/spring/ws/sayHello")
                .path("{name}").resolveTemplate("name", name)
                .request(MediaType.TEXT_HTML).async().get(new InvocationCallback<String>() {
                    @Override
                    public void completed(String s) {
                        System.out.println("Server returns: " + s);
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        System.err.println("Server throws error, msg = " + throwable.getMessage());
                    }
                });

        resp.get();
    }

    private static String randStr(int length) {
        StringBuilder sb = new StringBuilder();

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }

        return sb.toString();
    }
}
