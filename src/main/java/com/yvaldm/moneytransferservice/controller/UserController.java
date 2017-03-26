package com.yvaldm.moneytransferservice.controller;

import com.yvaldm.moneytransferservice.entity.User;
import com.yvaldm.moneytransferservice.service.UserService;
import com.yvaldm.moneytransferservice.service.impl.UserServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    @Produces(MediaType.APPLICATION_JSON)
    public List<User>  findAll() {
        Integer user1 = userService.create("asdasd");
        Integer user2 = userService.create("asdasqqq");
        List<User> users = userService.findAll();
        //String output = "Jersey say : " + msg;

        userService.delete(user1);
        userService.delete(user2);

        return users;
    }



}
