package com.outfittery.challenge.util;

import com.outfittery.challenge.model.Reservation;
import com.outfittery.challenge.model.Stylist;
import com.outfittery.challenge.model.params.StylistState;
import com.outfittery.challenge.model.params.TimeSlot;

import java.time.ZonedDateTime;

public class TestEntityCreator {


    public static Reservation createReservation() {
        Reservation model = new Reservation(
                "My Text", new TimeSlot(ZonedDateTime.now().plusMinutes(30), ZonedDateTime.now().plusMinutes(60)),
                "Customer Test");
        model.setId(1L);
        return model;
    }

    public static Stylist createStylist() {
        Stylist model = new Stylist(
                "My Name", StylistState.READY_TO_STYLE);
        model.setId(1L);
        return model;
    }
}
