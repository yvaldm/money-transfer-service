package com.yvaldm.moneytransferservice.dao.impl;

import com.yvaldm.moneytransferservice.dao.AccountDao;
import com.yvaldm.moneytransferservice.entity.Account;
import com.yvaldm.moneytransferservice.exception.BusinessException;
import com.yvaldm.moneytransferservice.exception.DataAccessException;
import com.yvaldm.moneytransferservice.helper.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.*;
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
            Statement statement = jdbcTemplate.getConnection().createStatement();
            final String sql = "SELECT * FROM ACCOUNTS";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setBalance(rs.getBigDecimal("balance"));
                account.setUserId(rs.getInt("user_id"));
                accountList.add(account);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error", e);
        }

        return accountList;
    }

    @Override
    public Account find(Integer accountId) {

        Account account = new Account();

        try {
            Connection connection = jdbcTemplate.getConnection();
            final String sql = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            rs.next();
            account.setBalance(rs.getBigDecimal("BALANCE"));
            account.setAccountId(rs.getInt("ACCOUNT_ID"));
            account.setUserId(rs.getInt("USER_ID"));
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error", e);
        }

        return account;
    }

    @Override
    public Integer create(BigDecimal balance, Integer userId) {

        Integer accountId;

        try {
            Connection connection = jdbcTemplate.getConnection();
            final String sql = "INSERT INTO ACCOUNTS(BALANCE, USER_ID) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBigDecimal(1,balance);
            ps.setInt(2, userId);
            ps.executeUpdate();
            accountId = jdbcTemplate.identity();
            ps.close();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error", e);
        }

        return accountId;
    }

    @Override
    public synchronized void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {

        Account fromAccount = find(fromAccountId);
        Account toAccount = find(toAccountId);
        BigDecimal balance = fromAccount.getBalance();

        // if balance of source account less then zero after withdraw, then throw error
        if(balance.compareTo(amount) < 0 ){
            String balanceStr = DECIMAL_FORMAT.format(balance);
            String amountStr = DECIMAL_FORMAT.format(amount);
            throw new BusinessException(
                    String.format("Unable to withdraw. Balance = %s, Withdraw amount = %s, AccountId = %d",
                            balanceStr, amountStr, fromAccountId));
        }

        BigDecimal balanceAfterWithdraw = balance.subtract(amount);
        BigDecimal toAccountBalance = toAccount.getBalance();
        BigDecimal balanceAfterAdd = toAccountBalance.add(amount);

        Connection connection = jdbcTemplate.getConnection();

        try {

            final String sql = "UPDATE ACCOUNTS SET BALANCE=? WHERE ACCOUNT_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBigDecimal(1, balanceAfterWithdraw);
            ps.setInt(2, fromAccountId);
            ps.executeUpdate();

            ps.setBigDecimal(1, balanceAfterAdd);
            ps.setInt(2, toAccountId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("SQL Error", e);
        }
    }

}
