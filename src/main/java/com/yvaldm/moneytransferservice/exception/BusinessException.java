package com.yvaldm.moneytransferservice.exception;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
