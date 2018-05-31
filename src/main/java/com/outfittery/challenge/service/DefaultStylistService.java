package com.outfittery.challenge.service;

import com.outfittery.challenge.exception.*;
import com.outfittery.challenge.model.Reservation;
import com.outfittery.challenge.model.Stylist;
import com.outfittery.challenge.model.params.StylistState;
import com.outfittery.challenge.model.params.TimeSlot;
import com.outfittery.challenge.repository.ReservationRepository;
import com.outfittery.challenge.repository.StylistRepository;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultStylistService implements StylistService {


    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultStylistService.class);

    private final StylistRepository stylistRepository;
    private final ReservationRepository reservationRepository;


    public DefaultStylistService(final StylistRepository stylistRepository, ReservationRepository reservationRepository) {
        this.stylistRepository = stylistRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Stylist create(Stylist entity) throws ConstraintsViolationException {
        Stylist entityDO;
        try {
            entityDO = stylistRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("Some constraints are thrown due to Stylist creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return entityDO;
    }

    @Override
    public void delete(Long id) {
        stylistRepository.delete(id);
    }

    @Override
    @Transactional
    public Stylist updateStylistState(Long id, StylistState state) throws EntityNotFoundException {
        Stylist entityDO = findEntityChecked(id);
        entityDO.setState(state);
        return entityDO;

    }


    @Override
    public Stylist find(Long id) throws EntityNotFoundException {
        return findEntityChecked(id);
    }

    @Override
    @Transactional
    public void makeReservation(Reservation reservation) throws TimeAlreadyInUseException, NotValidReservationTime, NotValidTimeSlot {

        if (!isValidTimeSlot(reservation.getTimeslot())) {
            throw new NotValidTimeSlot("Time slot is not valid");
        }

        long reservationCountForThisTime = reservationRepository.countAllByTimeslot_FromAndTimeslot_To(reservation.getTimeslot().getFromTime(), reservation.getTimeslot().getToTime());
        long numberOfAvailableStylists = stylistRepository.countAllByState(StylistState.READY_TO_STYLE);

        if (reservationCountForThisTime == numberOfAvailableStylists) {
            throw new TimeAlreadyInUseException("Not available stylist for this time");
        }
        List<Stylist> stylistNotAvailable = stylistRepository.findAllByReservations_Timeslot_fromAndReservations_Timeslot_toAndState(
                reservation.getTimeslot().getFromTime(), reservation.getTimeslot().getToTime(), StylistState.READY_TO_STYLE);

        List<Long> ids = extractIdsFromStylists(stylistNotAvailable);


        if (ids.isEmpty()) {
            ids.add(0L);
        }
        Stylist stylist = stylistRepository.findFirstByIdNotIn(ids);


        reservation.setStylist(stylist);

        reservationRepository.save(reservation);

        stylist.getReservations().add(reservation);
        stylistRepository.save(stylist);

    }

    public List<TimeSlot> findAvailableTimeSLotsForGivenTimeSlot(TimeSlot timeslot) throws NotValidTimeSlot {
        if (!isValidTimeSlot(timeslot)) {
            throw new NotValidTimeSlot("Time slot is not valid");
        }
        List<TimeSlot> timeSlots = new LinkedList<>();
        long numberOfAvailableStylists = stylistRepository.countAllByState(StylistState.READY_TO_STYLE);
        long reservationCountForThisTime = 0;
        ZonedDateTime startDate = timeslot.getFromTime().withMinute(0).withSecond(0); // make round hours
        ZonedDateTime endDay = timeslot.getToTime().withMinute(0).withSecond(0);
        // each 30 minutes; from start time (8:00) until end time (19:00). Working hours
        while (startDate.isBefore(endDay)) {
            reservationCountForThisTime = reservationRepository.countAllByTimeslot_FromAndTimeslot_To(startDate, startDate.plusMinutes(30));
            if (reservationCountForThisTime != numberOfAvailableStylists) {
                timeSlots.add(new TimeSlot(startDate, startDate.plusMinutes(30)));
            }
            startDate = startDate.plusMinutes(30);
        }
        return timeSlots;
    }

    private boolean isValidTimeSlot(TimeSlot timeSlot) {
        return (timeSlot.getFromTime().isAfter(ZonedDateTime.now()));
    }

    private List<Long> extractIdsFromStylists(List<Stylist> stylists) {
        return stylists.stream().map(Stylist::getId).collect(Collectors.toList());
    }


    private Stylist findEntityChecked(Long id) throws EntityNotFoundException {
        Stylist entityDO = stylistRepository.findOne(id);
        if (entityDO == null) {
            throw new EntityNotFoundException("Could not find entity with id: " + id);
        }
        return entityDO;
    }

}
