package com.yvaldm.moneytransferservice.controller.request;

import java.math.BigDecimal;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class TransactionRequest {

    private Integer fromAccountId;
    private Integer toAccountId;
    private BigDecimal amount;

    public TransactionRequest() {
    }

    public Integer getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Integer fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Integer getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Integer toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
