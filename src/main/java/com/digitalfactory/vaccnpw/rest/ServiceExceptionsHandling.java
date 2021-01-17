package com.digitalfactory.vaccnpw.rest;

import com.digitalfactory.vaccnpw.exceptions.CommonException;
import com.digitalfactory.vaccnpw.exceptions.MissingOrBadParameterException;
import com.digitalfactory.vaccnpw.exceptions.NoAvailableTimeReservationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ServiceExceptionsHandling {

    @ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "502")
    @ExceptionHandler(value = CommonException.class)
    public void handleCommonException() {
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "400")
    @ExceptionHandler(value = MissingOrBadParameterException.class)
    public void handleMissingOrBadParameterException() {
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "404")
    @ExceptionHandler(value = NoAvailableTimeReservationException.class)
    public void handleNoAvailableTimeReservationException() {
    }
}
