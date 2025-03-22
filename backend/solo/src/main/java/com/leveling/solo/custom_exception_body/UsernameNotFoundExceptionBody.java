package com.leveling.solo.custom_exception_body;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundExceptionBody extends AbstractExceptionBody {
    public UsernameNotFoundExceptionBody(String message, ZonedDateTime timeStamp, HttpStatus httpStatus) {
        super(message, timeStamp, httpStatus);
    }
}
