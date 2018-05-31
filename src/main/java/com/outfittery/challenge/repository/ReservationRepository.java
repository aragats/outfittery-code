package com.outfittery.challenge.repository;

import com.outfittery.challenge.model.Reservation;
import com.outfittery.challenge.model.Stylist;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.time.ZonedDateTime;

public interface ReservationRepository extends CrudRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {

    long countAllByTimeslot_FromAndTimeslot_To(ZonedDateTime from, ZonedDateTime to);
}
