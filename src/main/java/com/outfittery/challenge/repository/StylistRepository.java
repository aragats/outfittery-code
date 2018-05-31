package com.outfittery.challenge.repository;

import com.outfittery.challenge.model.Stylist;
import com.outfittery.challenge.model.params.StylistState;
import com.outfittery.challenge.model.params.TimeSlot;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.List;

public interface StylistRepository extends CrudRepository<Stylist, Long>, JpaSpecificationExecutor<Stylist> {

    long countAllByState(StylistState state);

    List<Stylist> findAllByReservations_Timeslot_fromAndReservations_Timeslot_toAndState(ZonedDateTime from, ZonedDateTime to, StylistState state);

    Stylist findFirstByIdNotIn(List<Long> ids);


}
