package com.yvaldm.moneytransferservice.service.impl;

import com.yvaldm.moneytransferservice.dao.UserDao;
import com.yvaldm.moneytransferservice.dao.impl.UserDaoImpl;
import com.yvaldm.moneytransferservice.entity.User;
import com.yvaldm.moneytransferservice.service.UserService;

import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User find(Integer userId) {
        return userDao.find(userId);
    }

    @Override
    public Integer create(String name) {
        return userDao.create(name);
    }
}
