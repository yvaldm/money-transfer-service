package com.yvaldm.moneytransferservice.controller;

import com.yvaldm.moneytransferservice.entity.User;
import com.yvaldm.moneytransferservice.service.UserService;
import com.yvaldm.moneytransferservice.service.impl.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
@Path("/users")
public class UserController {

    private UserService userService;

    public UserController() {
        userService = new UserServiceImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAll() {
        return userService.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user){
        Integer userId = userService.create(user.getName());
        return Response.ok(userId).build();
    }

}