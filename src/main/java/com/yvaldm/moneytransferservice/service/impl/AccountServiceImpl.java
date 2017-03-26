package com.yvaldm.moneytransferservice.service.impl;

import com.yvaldm.moneytransferservice.dao.AccountDao;
import com.yvaldm.moneytransferservice.dao.impl.AccountDaoImpl;
import com.yvaldm.moneytransferservice.entity.Account;
import com.yvaldm.moneytransferservice.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public AccountServiceImpl() {
        accountDao = new AccountDaoImpl();
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account find(Integer accountId) {
        return accountDao.find(accountId);
    }

    @Override
    public Integer create(BigDecimal balance, Integer userId) {
        return accountDao.create(balance, userId);
    }

    @Override
    public void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {
        accountDao.transfer(fromAccountId, toAccountId, amount);
    }

}
