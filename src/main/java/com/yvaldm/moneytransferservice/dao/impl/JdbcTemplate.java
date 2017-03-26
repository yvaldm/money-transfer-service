package com.yvaldm.moneytransferservice.dao.impl;

import java.sql.*;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class JdbcTemplate {

    private Connection connection;

    private static JdbcTemplate INSTANCE = null;

    public static JdbcTemplate getInstance(){
        if (INSTANCE == null){
            INSTANCE = new JdbcTemplate();
        }
        return INSTANCE;
    }

    private JdbcTemplate() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
             recreateSchema();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void recreateSchema() {
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:moneytransferservice", "sa", "");
            Statement statement = connection.createStatement();
            statement.execute("DROP SCHEMA PUBLIC CASCADE");
            statement.execute("CREATE TABLE USERS (USER_ID INTEGER IDENTITY, NAME VARCHAR(256))");
            statement.execute("CREATE TABLE ACCOUNTS (ACCOUNT_ID INTEGER IDENTITY, BALANCE NUMERIC, USER_ID INTEGER)");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        Statement st = null;
        try {
            st = connection.createStatement();
            st.execute("SHUTDOWN");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer insert(String expression) {
        Statement statement;
        Integer result = null;
        try {
            statement = connection.createStatement();
            statement.execute(expression);
            ResultSet resultSet = statement.executeQuery("CALL IDENTITY()");
            resultSet.next();
            Object identityObject = resultSet.getObject(1);
            if(identityObject instanceof Integer) {
                result = (Integer) identityObject;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet query(String expression) throws SQLException {
        Statement st;
        ResultSet rs = null;
        st = connection.createStatement();
        rs = st.executeQuery(expression);
        st.close();
        return rs;
    }
}