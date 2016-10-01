package com.cmcc.syw.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by sunyiwei on 16/10/1.
 */
public class RestServer {

    @GET
    @Path("/sayHello/{name}")
    @Produces("text/html")
    public void hello(@PathParam("name") String name) {
        System.out.println("Hello, " + name);
    }
}
