package com.cmcc.syw.webservice;

import com.cmcc.syw.model.User;

import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by sunyiwei on 16/10/1.
 */
public class RestServer {

    private static void parse(UriInfo uriInfo) {
        MultivaluedMap<String, String> map = uriInfo.getQueryParameters();
        printMap(map);

        MultivaluedMap<String, String> map1 = uriInfo.getPathParameters();
        printMap(map1);
    }

    private static void printMap(Map<String, List<String>> map) {
        for (String s : map.keySet()) {
            System.out.println(s + ": " + map.get(s));
        }
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    public User getUser() {
        User user = new User();
        user.setName("dfklasjfdlasjl");
        user.setPassword("dkfjlasdfjl");

        return user;
    }

    @GET
    @Path("/sayHello/{name}")
    @Produces("text/html")
    public Response hello(@PathParam("name") String name, @Context UriInfo uriInfo) {
        parse(uriInfo);

        Response.ResponseBuilder builder = new ResponseBuilderImpl();
        builder.status(Response.Status.BAD_REQUEST);

        return builder.build();
    }
}
