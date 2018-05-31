package com.outfittery.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Reservation time is not valid ...")
public class NotValidReservationTime extends Exception {

    private static final long serialVersionUID = -5982718708904030143L;

    public NotValidReservationTime(String message) {
        super(message);
    }

}
