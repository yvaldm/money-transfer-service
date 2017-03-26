package com.yvaldm.moneytransferservice.dao.impl;

import com.yvaldm.moneytransferservice.dao.UserDao;
import com.yvaldm.moneytransferservice.entity.User;
import com.yvaldm.moneytransferservice.exception.DataAccessException;
import com.yvaldm.moneytransferservice.helper.JdbcTemplate;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();

    @Override
    public List<User> findAll() {
        List<User> userList = new LinkedList<>();

        try {
            Connection connection = jdbcTemplate.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setUserId(resultSet.getInt("user_id"));
                userList.add(user);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error", e);
        }

        return userList;
    }

    @Override
    public User find(Integer userId) {

        User result = null;

        try {
            Connection connection = jdbcTemplate.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID=?");
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            result.setName(resultSet.getString("name"));
            result.setUserId(resultSet.getInt("user_id"));
            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error", e);
        }

        return result;
    }

    @Override
    public Integer create(String name) {

        Integer userId;

        try {
            Connection connection = jdbcTemplate.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO USERS(NAME) VALUES (?)");
            ps.setString(1, name);
            ps.execute();
            userId = jdbcTemplate.identity();
            ps.close();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error", e);
        }

        return userId;
    }

    @Override
    public void delete(Integer userId) {
        try {
            Connection connection = jdbcTemplate.getConnection();
            PreparedStatement ps =
                    connection.prepareStatement("DELETE FROM USERS WHERE USER_ID=?");
            ps.setInt(1, userId);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error", e);
        }
    }
}
