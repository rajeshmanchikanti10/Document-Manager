package com.phonepe.pheonepeMC.exceptions;

public class UserException extends Exception {
    String userId;

    public UserException(String userId, String message) {
        super(String.format("userId -%s %s", userId, message));
        this.userId = userId;

    }
}
