package com.virtualpet.exeption;

public class CannotParsePageException extends RuntimeException {
    public CannotParsePageException(String page) {
        super(String.format("Cannot parse string %s", page));
    }
}
