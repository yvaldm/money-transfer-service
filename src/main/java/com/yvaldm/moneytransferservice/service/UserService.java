package com.yvaldm.moneytransferservice.service;

import com.yvaldm.moneytransferservice.entity.User;

import java.util.List;

/**
 * User Service
 *
 * Created by valeryyakovlev on 26/03/2017.
 */
public interface UserService {

    /**
     * Find all User entities
     *
     * @return list of all user entities available in the database
     */
    List<User> findAll();

    /**
     * Find user entity by given id
     *
     * @param userId id of user
     * @return user details
     */
    User find(Integer userId);

    /**
     * Delete user by id
     *
     * @param userId is of user to be deleted
     */
    void delete(Integer userId);

    /**
     * Create new user entity with given name
     *
     * @param name user name
     * @return id of created user entity
     */
    Integer create(String name);
}
