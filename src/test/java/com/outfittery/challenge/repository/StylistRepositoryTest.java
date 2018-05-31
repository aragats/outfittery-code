package com.outfittery.challenge.repository;

import com.outfittery.challenge.Application;
import com.outfittery.challenge.model.Reservation;
import com.outfittery.challenge.model.Stylist;
import com.outfittery.challenge.model.params.StylistState;
import com.outfittery.challenge.model.params.TimeSlot;
import com.outfittery.challenge.util.MyAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StylistRepositoryTest {

    @Autowired
    private StylistRepository stylistRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void countAllByState() {
        stylistRepository.save(new Stylist("name", StylistState.ON_HOLIDAYS));
        long count = stylistRepository.countAllByState(StylistState.ON_HOLIDAYS);
        assertTrue(count == 1);
    }

    @Test
    public void findAllByReservations_Timeslot_fromAndReservations_Timeslot_toAndState() {
        Stylist stylist = new Stylist("name", StylistState.READY_TO_STYLE);
        ZonedDateTime dateTime = ZonedDateTime.now().withMinute(0).withSecond(0);
        Reservation reservation = new Reservation("My Comment", new TimeSlot(dateTime, dateTime.plusMinutes(30)), "My Custommer");
        stylistRepository.save(stylist);

        reservation.setStylist(stylist);
        reservationRepository.save(reservation);
        stylistRepository.save(stylist);
        List<Stylist> actual = stylistRepository.findAllByReservations_Timeslot_fromAndReservations_Timeslot_toAndState(
                reservation.getTimeslot().getFromTime(), reservation.getTimeslot().getToTime(), StylistState.READY_TO_STYLE);

        assertNotNull(actual);
        assertTrue(actual.size() != 0);
        MyAssert.assertEqualsStylist(stylist, actual.get(0));
    }

    @Test
    public void findFirstByIdNotIn() {

        Stylist stylist = stylistRepository.findFirstByIdNotIn(Collections.singletonList(0L));
        assertNotNull(stylist);
    }
}
