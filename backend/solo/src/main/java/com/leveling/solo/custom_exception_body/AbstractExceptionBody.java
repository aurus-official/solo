package com.leveling.solo.custom_exception_body;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public abstract class AbstractExceptionBody {

    private final String message;
    private final ZonedDateTime timeStamp;
    private final HttpStatus httpStatus;

    AbstractExceptionBody(String message, ZonedDateTime timeStamp, HttpStatus httpStatus) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
