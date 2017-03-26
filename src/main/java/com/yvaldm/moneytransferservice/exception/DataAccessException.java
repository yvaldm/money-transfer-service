package com.yvaldm.moneytransferservice.exception;

/**
 * Created by valeryyakovlev on 26/03/2017.
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
