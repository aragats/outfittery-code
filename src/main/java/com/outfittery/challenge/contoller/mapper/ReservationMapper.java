package com.outfittery.challenge.contoller.mapper;

import com.outfittery.challenge.dto.ReservationDTO;
import com.outfittery.challenge.model.Reservation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper {

    public static Reservation makeReservation(ReservationDTO modelDTO) {
        return new Reservation(modelDTO.getComment(), modelDTO.getTimeslot(), modelDTO.getCustomer());
    }


    public static ReservationDTO makeReservationDTO(Reservation model) {
        ReservationDTO.ReservationDTOBuilder modelDTOBuilder = ReservationDTO.newBuilder()
                .setId(model.getId())
                .setComment(model.getComment())
                .setTimeslot(model.getTimeslot())
                .setCustomer(model.getCustomer());
        return modelDTOBuilder.createModelDTO();
    }


    public static List<ReservationDTO> makeReservationDTOList(Collection<Reservation> models) {
        return models.stream()
                .map(ReservationMapper::makeReservationDTO)
                .collect(Collectors.toList());
    }
}
