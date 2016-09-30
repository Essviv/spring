package com.cmcc.syw.service;

import com.cmcc.syw.model.Model;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sunyiwei on 16/9/29.
 */
@Consumes(value = MediaType.APPLICATION_XML)
@Produces(value = MediaType.APPLICATION_XML)
public interface ModelService {
    @Path("/users/{name}")
    @GET
    Model get(@PathParam("name") String name);

    @Path("/users")
    @POST
    Response create(Model model);
}
