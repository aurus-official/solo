package com.leveling.solo.exception_handling;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import com.leveling.solo.custom_exception.PlayerNotExistException;
import com.leveling.solo.custom_exception_body.PlayerNotExistExceptionBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { PlayerNotExistException.class })
    ResponseEntity<PlayerNotExistExceptionBody> handlePlayerNotExistException(PlayerNotExistException e) {
        PlayerNotExistExceptionBody playerNotExistExceptionBody = new PlayerNotExistExceptionBody(
                e.getMessage(), ZonedDateTime.now(ZoneId.of("Z")), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<PlayerNotExistExceptionBody>(playerNotExistExceptionBody, HttpStatus.BAD_REQUEST);
    }
}
