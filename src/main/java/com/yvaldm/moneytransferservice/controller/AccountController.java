package com.yvaldm.moneytransferservice.controller;

import com.yvaldm.moneytransferservice.service.AccountService;
import com.yvaldm.moneytransferservice.service.impl.AccountServiceImpl;

import javax.ws.rs.Path;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */

@Path("/accounts")
public class AccountController {

    private AccountService accountService;

    AccountController(){
        accountService = new AccountServiceImpl();
    }


}
