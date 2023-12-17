package com.jstar.phone.exception;

public class PhoneNotBookedException extends RuntimeException {
    public PhoneNotBookedException(String message) {
        super(message);
    }
}