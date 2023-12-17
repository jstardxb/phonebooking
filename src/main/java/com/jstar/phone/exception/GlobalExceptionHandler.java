package com.jstar.phone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PhoneNotFoundException.class)
    public ResponseEntity<String> handlePhoneNotFound(PhoneNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhoneNotAvailableException.class)
    public ResponseEntity<Object> handlePhoneNotAvailable(PhoneNotAvailableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhoneNotBookedException.class)
    public ResponseEntity<Object> handlePhoneNotAvailable(PhoneNotBookedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        var errorMap = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return errorMap;
    }
}