package com.yvaldm.moneytransferservice.dao;

import com.yvaldm.moneytransferservice.entity.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public interface AccountDao {

    List<Account> findAll();

    Account find(Integer accountId);

    Integer create(BigDecimal balance, Integer userId);

    void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount);

}
