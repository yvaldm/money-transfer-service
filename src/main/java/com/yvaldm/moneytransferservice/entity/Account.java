package com.yvaldm.moneytransferservice.entity;

import java.math.BigDecimal;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class Account {

    private Integer accountId;
    private BigDecimal balance;
    private Integer userId;

    public Account() {
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
