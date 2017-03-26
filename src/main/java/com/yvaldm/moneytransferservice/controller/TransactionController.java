package com.yvaldm.moneytransferservice.controller;

import com.yvaldm.moneytransferservice.controller.request.TransactionRequest;
import com.yvaldm.moneytransferservice.service.AccountService;
import com.yvaldm.moneytransferservice.service.impl.AccountServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
@Path("/transactions")
public class TransactionController {

    private AccountService accountService;

    public TransactionController(){
        accountService = new AccountServiceImpl();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transaction(TransactionRequest transactionRequest) {
        accountService.transfer(transactionRequest.getFromAccountId(),
                transactionRequest.getToAccountId(), transactionRequest.getAmount());
        return Response.ok().build();
    }
}
