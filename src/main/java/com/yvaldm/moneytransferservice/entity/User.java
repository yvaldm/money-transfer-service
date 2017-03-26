package com.yvaldm.moneytransferservice.entity;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class User {

    private Integer userId;
    private String name;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
