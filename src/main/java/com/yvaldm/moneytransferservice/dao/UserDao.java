package com.yvaldm.moneytransferservice.dao;

import com.yvaldm.moneytransferservice.entity.User;

import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public interface UserDao {

    List<User> findAll();

    User find(Integer userId);

    Integer create(String name);

    void delete(Integer userId);

}
