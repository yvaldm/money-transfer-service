package com.yvaldm.moneytransferservice.controller;

import com.yvaldm.moneytransferservice.service.UserService;
import com.yvaldm.moneytransferservice.service.impl.UserServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
@Path("/users")
public class UserController {

    UserService userService;

    public UserController() {
        userService = new UserServiceImpl();
    }

    @GET
    public Response getMsg(@PathParam("param") String msg) {


        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();

    }

}
