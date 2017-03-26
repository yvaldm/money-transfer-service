package com.yvaldm.moneytransferservice.service.impl;

import com.yvaldm.moneytransferservice.entity.Account;
import com.yvaldm.moneytransferservice.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class AccountServiceImpl implements AccountService {

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account find(Integer accountId) {
        return null;
    }

    @Override
    public void create(BigDecimal balance, Integer userId) {

    }

    @Override
    public void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {
        return;
    }

}
