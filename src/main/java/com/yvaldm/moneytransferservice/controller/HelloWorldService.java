package com.yvaldm.moneytransferservice.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
@Path("/hellojersey")
public class HelloWorldService {

    @GET
    //@Path("/{param}")
    //public Response getMsg(@PathParam("param") String msg) {
    public Response getMsg(@PathParam("param") String msg) {

        String output = "Jersey say : " + msg;


        return Response.status(200).entity(output).build();

    }

}