package com.outfittery.challenge.service;

import com.outfittery.challenge.exception.*;
import com.outfittery.challenge.model.Reservation;
import com.outfittery.challenge.model.Stylist;
import com.outfittery.challenge.model.params.StylistState;
import com.outfittery.challenge.repository.ReservationRepository;
import com.outfittery.challenge.repository.StylistRepository;
import com.outfittery.challenge.util.MyAssert;
import com.outfittery.challenge.util.TestEntityCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DefaultStylistServiceTest {

    private static StylistRepository stylistRepository;
    private static ReservationRepository reservationRepository;

    @Autowired
    private StylistService service;

    @TestConfiguration
    static class StylistServiceImplTestContextConfiguration {

        @Bean
        public StylistService stylistService() {
            stylistRepository = Mockito.mock(StylistRepository.class);
            reservationRepository = Mockito.mock(ReservationRepository.class);

            return new DefaultStylistService(stylistRepository, reservationRepository);
        }
    }


    @Test
    public void testCreate() throws ConstraintsViolationException {
        Stylist expected = TestEntityCreator.createStylist();
        when(stylistRepository.save(expected)).thenReturn(expected);

        Stylist actual = service.create(expected);
        MyAssert.assertEqualsStylist(expected, actual);
    }

    @Test
    public void testDelete() {
        Stylist expected = TestEntityCreator.createStylist();
        doNothing().when(stylistRepository).delete(expected.getId());
        service.delete(expected.getId());
    }

    @Test
    public void testUpdateStylistState() throws EntityNotFoundException {
        Stylist expected = TestEntityCreator.createStylist();
        Stylist expectedUpdated = TestEntityCreator.createStylist();
        expectedUpdated.setState(StylistState.SICK);


        when(stylistRepository.findOne(expected.getId())).thenReturn(expected);
        Stylist actual = service.updateStylistState(expected.getId(), expectedUpdated.getState());
        MyAssert.assertEqualsStylist(expectedUpdated, actual);
    }

    @Test
    public void testFind() throws EntityNotFoundException {
        Stylist expected = TestEntityCreator.createStylist();
        Mockito.when(stylistRepository.findOne(expected.getId())).thenReturn(expected);
        Stylist actual = service.find(expected.getId());
        MyAssert.assertEqualsStylist(expected, actual);
    }


    @Test
    public void makeReservation() throws EntityNotFoundException, NotValidReservationTime, TimeAlreadyInUseException, NotValidTimeSlot {
        Reservation expectedReservation = TestEntityCreator.createReservation();
        Stylist expectedStylist = TestEntityCreator.createStylist();
        Mockito.when(reservationRepository.countAllByTimeslot_FromAndTimeslot_To(expectedReservation.getTimeslot().getFromTime(), expectedReservation.getTimeslot().getToTime())).thenReturn(0L);


        Mockito.when(stylistRepository.countAllByState(StylistState.READY_TO_STYLE)).thenReturn(1L);
        Mockito.when(stylistRepository.findAllByReservations_Timeslot_fromAndReservations_Timeslot_toAndState(
                expectedReservation.getTimeslot().getFromTime(), expectedReservation.getTimeslot().getToTime(), StylistState.READY_TO_STYLE))
                .thenReturn(Collections.singletonList(expectedStylist));

        Mockito.when(stylistRepository.findFirstByIdNotIn(Mockito.anyListOf(Long.class))).thenReturn(expectedStylist);

        when(reservationRepository.save(expectedReservation)).thenReturn(expectedReservation);
        when(stylistRepository.save(expectedStylist)).thenReturn(expectedStylist);



        service.makeReservation(expectedReservation);

    }

    @Test
    public void isAvailableStylistForGivenTime() {
    }

    @Test
    public void findAvailableTimeSLotsForGivenTimeSlot() {
    }
}
