package com.outfittery.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Timeslot is not valid ...")
public class NotValidTimeSlot extends Exception {


    public NotValidTimeSlot(String message) {
        super(message);
    }

}
