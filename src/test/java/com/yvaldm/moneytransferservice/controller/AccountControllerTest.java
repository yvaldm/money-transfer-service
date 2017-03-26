package com.yvaldm.moneytransferservice.controller;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.annotations.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.testng.Assert.assertEquals;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class AccountControllerTest extends JerseyTestNg.ContainerPerClassTest {


    @Override
    protected Application configure() {
        return new ResourceConfig(HelloWorldService.class);
    }

    @Test(priority = 1)
    public void first() throws Exception {
        test(1);
    }

    private void test(final Integer expected) {
        final Response response = target("/hellojersey").request().get();

        assertEquals(response.getStatus(), 200);
        //assertEquals(response.readEntity(Integer.class), expected);
    }

}
