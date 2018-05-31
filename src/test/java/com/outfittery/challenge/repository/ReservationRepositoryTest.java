package com.outfittery.challenge.repository;

import com.outfittery.challenge.Application;
import com.outfittery.challenge.model.Reservation;
import com.outfittery.challenge.model.Stylist;
import com.outfittery.challenge.model.params.StylistState;
import com.outfittery.challenge.model.params.TimeSlot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private StylistRepository stylistRepository;


    @Test
    public void countAllByTimeslot_FromAndTimeslot_To() {
        Stylist stylist = new Stylist("name R", StylistState.READY_TO_STYLE);

        ZonedDateTime dateTime = ZonedDateTime.now().withMinute(0).withSecond(0);
        Reservation reservation = new Reservation("My Comment C", new TimeSlot(dateTime, dateTime.plusMinutes(30)), "My Customer C");
        stylistRepository.save(stylist);

        reservation.setStylist(stylist);
        reservationRepository.save(reservation);

        long count = reservationRepository.countAllByTimeslot_FromAndTimeslot_To(reservation.getTimeslot().getFromTime(), reservation.getTimeslot().getToTime());

        assertTrue(count == 1);

    }
}
