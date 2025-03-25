package com.leveling.solo.custom_exception;

public class PlayerNotExistException extends Exception {
    public PlayerNotExistException(String message) {
        super(message);
    }

}
