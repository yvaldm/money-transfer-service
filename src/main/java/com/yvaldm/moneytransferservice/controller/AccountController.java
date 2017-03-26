package com.yvaldm.moneytransferservice.controller;

import com.yvaldm.moneytransferservice.entity.Account;
import com.yvaldm.moneytransferservice.service.AccountService;
import com.yvaldm.moneytransferservice.service.impl.AccountServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */

@Path("/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(){
        accountService = new AccountServiceImpl();
    }

    @GET
    @Path("{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account find(@PathParam("accountId") Integer accountId){
        return accountService.find(accountId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        Integer accountId = accountService.create(account.getBalance(), account.getUserId());
        return Response.ok(accountId).build();
    }
}
