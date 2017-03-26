package com.yvaldm.moneytransferservice.controller;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.annotations.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class UserControllerTest extends JerseyTestNg.ContainerPerClassTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(UserController.class);
    }

    @Test
    public void listUsers() throws Exception {
        final Response response = target("/users").request().get();
        //assertEquals(response.getStatus(), 200);
        //assertEquals(response.readEntity(Integer.class), expected);
    }

    @Test
    public void createUser() throws Exception {
//        final Response response = target("/users").request().post();
//        assertEquals(response.getStatus(), 200);
        //assertEquals(response.readEntity(Integer.class), expected);
    }


}
