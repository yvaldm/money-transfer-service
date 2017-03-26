package com.yvaldm.moneytransferservice.dao.impl;

import java.sql.*;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */

public class Testdb {

    Connection conn;

    public Testdb() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        conn = DriverManager.getConnection("jdbc:hsqldb:moneytransferservice", "sa", "");
    }

    public void shutdown() throws SQLException {
        Statement st = conn.createStatement();
        st.execute("SHUTDOWN");
        conn.close();
    }

    public synchronized void query(String expression) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        rs = st.executeQuery(expression);
        dump(rs);
        st.close();
    }

    public synchronized void update(String expression) throws SQLException {

        Statement st = null;

        st = conn.createStatement();

        int i = st.executeUpdate(expression);

        if (i == -1) {
            System.out.println("db error : " + expression);
        }

        st.close();
    }    // void update()

    public static void dump(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int colmax = meta.getColumnCount();
        int i;
        Object o = null;

        for (; rs.next(); ) {
            for (i = 0; i < colmax; ++i) {
                o = rs.getObject(i + 1);

                // with 1 not 0
                System.out.print(o.toString() + " ");
            }

            System.out.println(" ");
        }
    }

    public static void main(String[] args) {

        Testdb db = null;

        try {
            db = new Testdb();
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return;
        }

        try {

            db.update("CREATE TABLE USERS (USER_ID INTEGER IDENTITY, NAME VARCHAR(256))");

            db.update("CREATE TABLE ACCOUNTS (ACCOUNT_ID INTEGER IDENTITY, BALANCE NUMERIC, USER_ID INTEGER)");


        } catch (SQLException ex2) {

        }

        try {
            db.update("INSERT INTO USERS(USER_ID, NAME) VALUES(1, 'SAMPLE')");

            db.update("INSERT INTO ACCOUNTS(ACCOUNT_ID, BALANCE, USER_ID) VALUES(1, 333.12, 1)");

            // do a query
            db.query("SELECT * FROM USERS");
            db.query("SELECT * FROM ACCOUNTS");

            // at end of program
            db.shutdown();
        } catch (SQLException ex3) {
            ex3.printStackTrace();
        }
    }
}