package com.digitalfactory.vaccnpw.exceptions;

public class MissingOrBadParameterException extends RuntimeException {
    public MissingOrBadParameterException(String msg) {
        super(msg);
    }
}
