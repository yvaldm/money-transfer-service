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

        IntStream.range(0, 10).forEach(i -> accountService.create(new BigDecimal(100.0), userId));

        List<Account> accounts = accountService.findAll();
        assertTrue(accounts.size() == 10);
        Account account = accounts.get(0);
        BigDecimal balance = account.getBalance();
        assertTrue(new BigDecimal(100.0).equals(balance));

        Account account1 = accounts.get(1);
        Account account2 = accounts.get(2);

        accountService.transfer(account1.getAccountId(), account2.getAccountId(), new BigDecimal(53.2));

        Account account1AfterTransfer = accountService.find(account1.getAccountId());
        Account account2AfterTransfer = accountService.find(account2.getAccountId());

        BigDecimal balance1 = account1AfterTransfer.getBalance();
        BigDecimal balance2 = account2AfterTransfer.getBalance();

        BigDecimal sumAcc1Acc2 = balance1.add(balance2);
        BigDecimal expectedSum = new BigDecimal(200.0);
        assertTrue(sumAcc1Acc2.compareTo(expectedSum) == 0);

    }
}
