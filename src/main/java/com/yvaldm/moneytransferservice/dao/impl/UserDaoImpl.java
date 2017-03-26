package com.yvaldm.moneytransferservice.dao.impl;

import com.yvaldm.moneytransferservice.dao.UserDao;
import com.yvaldm.moneytransferservice.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
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

            ResultSet query = jdbcTemplate.query("SELECT * FROM USERS");

            while (query.next()){

                User user = new User();
                user.setName(query.getString("name"));
                user.setUserId(query.getInt("user_id"));
                userList.add(user);

            }

            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public User find(Integer userId) {

        User result = new User();

        try {
            ResultSet query = jdbcTemplate.query("SELECT * FROM USERS WHERE USER_ID=" + userId);
            query.next();
            result.setName(query.getString("name"));
            result.setUserId(query.getInt("user_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer create(String name) {
        return jdbcTemplate.insert(String.format("INSERT INTO USERS(NAME) VALUES ('%s')", name));
    }

    @Override
    public void delete(Integer userId) {
        jdbcTemplate.update("DELETE FROM USERS WHERE USER_ID=" + userId);
    }
}
