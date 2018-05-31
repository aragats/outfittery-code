package com.outfittery.challenge.contoller.mapper;

import com.outfittery.challenge.dto.StylistDTO;
import com.outfittery.challenge.model.Stylist;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StylistMapper {


    public static Stylist makeStylist(StylistDTO modelDTO) {
        return new Stylist(modelDTO.getName(), modelDTO.getState());
    }


    public static StylistDTO makeStylistDTO(Stylist model) {
        StylistDTO.StylistDTOBuilder modelDTOBuilder = StylistDTO.newBuilder()
                .setId(model.getId())
                .setName(model.getName())
                .setState(model.getState());
        return modelDTOBuilder.createStylistDTO();
    }


    public static List<StylistDTO> makeStylistDTOList(Collection<Stylist> models) {
        return models.stream()
                .map(StylistMapper::makeStylistDTO)
                .collect(Collectors.toList());
    }
}
