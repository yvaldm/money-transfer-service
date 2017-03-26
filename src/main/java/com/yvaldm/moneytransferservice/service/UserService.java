package com.yvaldm.moneytransferservice.service;

import com.yvaldm.moneytransferservice.entity.User;

import java.util.List;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public interface UserService {

    List<User> findAll();

    User find(Integer userId);

    Integer create(String name);

}
