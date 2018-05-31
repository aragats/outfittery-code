package com.outfittery.challenge.service;

import com.outfittery.challenge.exception.*;
import com.outfittery.challenge.model.Reservation;
import com.outfittery.challenge.model.Stylist;
import com.outfittery.challenge.model.params.StylistState;
import com.outfittery.challenge.model.params.TimeSlot;

import java.time.ZonedDateTime;
import java.util.List;

public interface StylistService {

    Stylist create(Stylist stylist) throws ConstraintsViolationException;

    void delete(Long id);

    Stylist updateStylistState(Long id, StylistState state) throws EntityNotFoundException;

    Stylist find(Long id) throws EntityNotFoundException;

    void makeReservation(Reservation reservation) throws EntityNotFoundException, TimeAlreadyInUseException, NotValidReservationTime, NotValidTimeSlot;

    List<TimeSlot> findAvailableTimeSLotsForGivenTimeSlot(TimeSlot timeslot) throws NotValidTimeSlot;

}
