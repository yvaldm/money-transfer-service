package com.yvaldm.moneytransferservice.controller;

import com.yvaldm.moneytransferservice.controller.request.TransactionRequest;
import com.yvaldm.moneytransferservice.entity.Account;
import com.yvaldm.moneytransferservice.entity.User;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.junit.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class IntegrationControllerTest extends JerseyTestNg.ContainerPerClassTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(new HashSet<>(Arrays.asList(AccountController.class, UserController.class,
                TransactionController.class)));
    }

    @Test
    public void integrationTest() throws Exception {

        // create user
        User user = new User();
        user.setName("UserForTranscation");
        Entity<User> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
        final Response userResponse = target("/users").request().post(userEntity);
        Integer userId = userResponse.readEntity(Integer.class);
        Assert.assertNotNull(userId);

        // create account for newly created user
        Account accountRequest = new Account();
        accountRequest.setBalance(new BigDecimal(100.0));
        accountRequest.setUserId(userId);

        // account #1
        Entity<Account> accountEntity = Entity.entity(accountRequest, MediaType.APPLICATION_JSON);
        final Response accountResponse = target("/accounts").request().post(accountEntity);
        assertEquals(accountResponse.getStatus(), 200);
        Integer account1Id = accountResponse.readEntity(Integer.class);
        assertNotNull(account1Id);

        // account #2
        Entity<Account> account2Entity = Entity.entity(accountRequest, MediaType.APPLICATION_JSON);
        final Response account2Response = target("/accounts").request().post(account2Entity);
        assertEquals(account2Response.getStatus(), 200);
        Integer account2Id = account2Response.readEntity(Integer.class);
        assertNotNull(account2Id);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new BigDecimal(32.65));
        transactionRequest.setFromAccountId(account1Id);
        transactionRequest.setToAccountId(account2Id);

        // perform transaction
        Entity<TransactionRequest> transactionRequestEntity = Entity.entity(transactionRequest, MediaType.APPLICATION_JSON);
        final Response transactionResponse = target("/transactions").request().post(transactionRequestEntity);
        assertEquals(transactionResponse.getStatus(), 200);

        // assert that amount was correctly withdrawn from source account and added to destination account
        Response account1AfterWithdrawResponse = target("/accounts/" + account1Id).request().get();
        Account account1AfterWithdraw = account1AfterWithdrawResponse.readEntity(Account.class);

        Response account2AfterWithdrawResponse = target("/accounts/" + account2Id).request().get();
        Account account2AfterWithdraw = account2AfterWithdrawResponse.readEntity(Account.class);

        BigDecimal balance1 = account1AfterWithdraw.getBalance();
        BigDecimal balance2 = account2AfterWithdraw.getBalance();

        // assert that balance1 + balance2 gives total balance (i.e. no money lost)
        BigDecimal sumAcc1Acc2 = balance1.add(balance2);
        BigDecimal expectedSum = new BigDecimal(200.0);
        assertTrue(sumAcc1Acc2.compareTo(expectedSum) == 0);

    }
}
