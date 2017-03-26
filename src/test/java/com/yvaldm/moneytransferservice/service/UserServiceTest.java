package com.yvaldm.moneytransferservice.service;

import com.yvaldm.moneytransferservice.entity.User;
import com.yvaldm.moneytransferservice.service.impl.UserServiceImpl;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */

public class UserServiceTest {

    public static final int SAMPLE_USER_ID = 1;

    private UserService userService = new UserServiceImpl();

    @Test
    public void integrationTest(){

        IntStream.range(0,5).forEach(i -> userService.create("sampleUser"+i));
        User user = userService.find(SAMPLE_USER_ID);
        assertNotNull(user);
        assertTrue("sampleUser1".equals(user.getName()));
        List<User> users = userService.findAll();
        assertTrue(users.size() == 5);

    }

}
