package com.outfittery.challenge.contoller;

import com.outfittery.challenge.contoller.mapper.ReservationMapper;
import com.outfittery.challenge.contoller.mapper.StylistMapper;
import com.outfittery.challenge.dto.ReservationDTO;
import com.outfittery.challenge.dto.StylistDTO;
import com.outfittery.challenge.exception.*;
import com.outfittery.challenge.model.Reservation;
import com.outfittery.challenge.model.Stylist;
import com.outfittery.challenge.model.params.StylistState;
import com.outfittery.challenge.model.params.TimeSlot;
import com.outfittery.challenge.service.StylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/stylists")
public class StylistController {

    @Autowired
    private StylistService service;


    @GetMapping("/{stylistId}")
    public StylistDTO getStylist(@Valid @PathVariable long stylistId) throws EntityNotFoundException {
        return StylistMapper.makeStylistDTO(service.find(stylistId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StylistDTO createStylist(@Valid @RequestBody StylistDTO stylistDTO) throws ConstraintsViolationException {
        Stylist stylistDO = StylistMapper.makeStylist(stylistDTO);
        return StylistMapper.makeStylistDTO(service.create(stylistDO));
    }


    @DeleteMapping("/{stylistId}")
    public void deleteStylist(@Valid @PathVariable long stylistId) {
        service.delete(stylistId);
    }

    @PutMapping("/{stylistId}/{styleState}")
    public void updateStylist(@Valid @PathVariable long stylistId, @Valid @PathVariable String styleState)
            throws EntityNotFoundException, ConstraintsViolationException {
        StylistState state;
        try {
            state = StylistState.valueOf(styleState.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ConstraintsViolationException("Illegal stylist state value = " + styleState);
        }
        service.updateStylistState(stylistId, state);
    }


    @PostMapping("/reservation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReservation(@Valid @RequestBody ReservationDTO reservationDTO) throws EntityNotFoundException, NotValidReservationTime, TimeAlreadyInUseException, NotValidTimeSlot {
        Reservation reservationDO = ReservationMapper.makeReservation(reservationDTO);
        service.makeReservation(reservationDO);
    }


    @PostMapping("/availability")
    public List<TimeSlot> getStylistAvailabilities(@Valid @RequestBody TimeSlot timeSlot) throws NotValidTimeSlot {
        return service.findAvailableTimeSLotsForGivenTimeSlot(timeSlot);
    }

}
