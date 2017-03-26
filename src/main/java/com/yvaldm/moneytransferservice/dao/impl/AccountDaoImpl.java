package com.yvaldm.moneytransferservice.dao.impl;

import com.yvaldm.moneytransferservice.dao.AccountDao;
import com.yvaldm.moneytransferservice.entity.Account;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class AccountDaoImpl implements AccountDao {

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.##");

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

        Account account = new Account();

        try {
            ResultSet query = jdbcTemplate.query("SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID=" + accountId);
            query.next();
            account.setBalance(query.getBigDecimal("BALANCE"));
            account.setAccountId(query.getInt("ACCOUNT_ID"));
            account.setUserId(query.getInt("USER_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Integer create(BigDecimal balance, Integer userId) {
        PreparedStatement preparedStatement;
        Integer accountId = null;
        try {
            preparedStatement = jdbcTemplate.getConnection().prepareStatement("INSERT INTO ACCOUNTS(BALANCE, USER_ID) VALUES (?, ?)");
            preparedStatement.setBigDecimal(1,balance);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            Statement statement = jdbcTemplate.getConnection().createStatement();
            accountId = jdbcTemplate.identity(statement);
            statement.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountId;
    }

    @Override
    public synchronized void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {

        // get balance in source
        // if balance in source < amount -> error

        Account fromAccount = find(fromAccountId);
        Account toAccount = find(toAccountId);
        BigDecimal balance = fromAccount.getBalance();
        if(balance.compareTo(amount) < 0 ){
            String balanceStr = DECIMAL_FORMAT.format(balance);
            String amountStr = DECIMAL_FORMAT.format(amount);
            throw new RuntimeException(
                    String.format("Unable to withdraw. Balance = %s, Withdraw amount = %s, AccountId = %d",
                            balanceStr, amountStr, fromAccountId));
        }

        BigDecimal balanceAfterWithdraw = balance.subtract(amount);

        jdbcTemplate.update(String.format("UPDATE ACCOUNTS SET BALANCE = %s WHERE ACCOUNT_ID=%d", balanceAfterWithdraw,
                fromAccountId));

        BigDecimal toAccountBalance = toAccount.getBalance();
        BigDecimal balanceAfterAdd = toAccountBalance.add(amount);

        jdbcTemplate.update(String.format("UPDATE ACCOUNTS SET BALANCE = %s WHERE ACCOUNT_ID=%d",
                balanceAfterAdd,
                toAccountId));

    }
}
