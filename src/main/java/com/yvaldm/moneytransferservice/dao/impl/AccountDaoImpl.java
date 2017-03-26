package com.yvaldm.moneytransferservice.dao.impl;

import com.yvaldm.moneytransferservice.dao.AccountDao;
import com.yvaldm.moneytransferservice.entity.Account;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new LinkedList<>();

        try {

            ResultSet rs = jdbcTemplate.query("SELECT * FROM ACCOUNTS");
            while (rs.next()){
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setBalance(rs.getBigDecimal("balance"));
                account.setUserId(rs.getInt("user_id"));
                accountList.add(account);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    @Override
    public Account find(Integer accountId) {
        return null;
    }

    @Override
    public Integer create(BigDecimal balance, Integer userId) {
        String balanceStr = new DecimalFormat("#0.##").format(balance);
        return jdbcTemplate.insert(String.format("INSERT INTO ACCOUNTS(BALANCE, USER_ID) VALUES (%s, %d)", balanceStr, userId));
    }

    @Override
    public void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {

    }
}
