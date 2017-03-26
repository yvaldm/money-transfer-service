package com.yvaldm.moneytransferservice.service;

import com.yvaldm.moneytransferservice.entity.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Account Service
 *
 * Created by valeryyakovlev on 26/03/2017.
 */
public interface AccountService {

    /**
     * Find all Account entities
     *
     * @return list of all account entities available in the database
     */
    List<Account> findAll();

    /**
     * Find single account entity by id
     *
     * @param accountId of account entity
     * @return details found account entity
     */
    Account find(Integer accountId);

    /**
     * Create new account with given balance belonging to specified user
     * @param balance amount of money on account
     * @param userId id of user
     * @return id of newly created account
     */
    Integer create(BigDecimal balance, Integer userId);

    /**
     * Performs money transfer operation
     *
     * @param fromAccountId source account id
     * @param toAccountId destination account id
     * @param amount money amount
     */
    void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount);

}
