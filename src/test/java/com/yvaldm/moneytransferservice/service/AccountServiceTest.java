package com.yvaldm.moneytransferservice.service;

import com.yvaldm.moneytransferservice.entity.Account;
import com.yvaldm.moneytransferservice.entity.User;
import com.yvaldm.moneytransferservice.service.impl.AccountServiceImpl;
import com.yvaldm.moneytransferservice.service.impl.UserServiceImpl;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class AccountServiceTest {

    private AccountService accountService = new AccountServiceImpl();

    private UserService userService = new UserServiceImpl();

    @Test
    public void integrationTest(){

        IntStream.range(0, 10).forEach(i -> userService.create("sample_user_"+i));

        List<User> users = userService.findAll();
        assertTrue(users.size() == 10);
        User user = users.get(0);
        Integer userId = user.getUserId();
        accountService.create(new BigDecimal(100.0), userId);
        List<Account> accounts = accountService.findAll();
        assertTrue(accounts.size() == 1);
        Account account = accounts.get(0);
        BigDecimal balance = account.getBalance();
        assertTrue(new BigDecimal(100.0).equals(balance));
    }
}
