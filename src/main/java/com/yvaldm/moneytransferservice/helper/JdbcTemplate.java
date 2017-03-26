package com.yvaldm.moneytransferservice.helper;

import com.yvaldm.moneytransferservice.exception.DataAccessException;

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

            connection = DriverManager.getConnection("jdbc:hsqldb:moneytransferservice", "sa", "");
            Statement statement = connection.createStatement();
            statement.execute("DROP SCHEMA PUBLIC CASCADE");
            statement.execute("CREATE TABLE USERS (USER_ID INTEGER IDENTITY, NAME VARCHAR(256))");
            statement.execute("CREATE TABLE ACCOUNTS (ACCOUNT_ID INTEGER IDENTITY, BALANCE NUMERIC, USER_ID INTEGER)");
            statement.close();

        } catch (Exception e) {
            throw new DataAccessException("SQL Error", e);
        }
    }

    /**
     * Method for retrieving id of newly created entity
     *
     * @return Integer id of previously created entity
     */
    public Integer identity(){

        Integer result = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("CALL IDENTITY()");
            resultSet.next();
            Object identityObject = resultSet.getObject(1);
            if (identityObject instanceof Integer) {
                result = (Integer) identityObject;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e ){
            throw new DataAccessException("SQL Error", e);
        }

        return result;
    }

    public Connection getConnection() {
        return connection;
    }
}