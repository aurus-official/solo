package com.leveling.solo.custom_exception_body;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class PlayerNotExistExceptionBody extends AbstractExceptionBody {
    public PlayerNotExistExceptionBody(String message, ZonedDateTime timeStamp, HttpStatus httpStatus) {
        super(message, timeStamp, httpStatus);
    }
}
