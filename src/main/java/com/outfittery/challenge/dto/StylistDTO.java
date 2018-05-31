package com.outfittery.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.outfittery.challenge.model.params.StylistState;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StylistDTO {

    @JsonIgnore
    private Long id;
    @NotNull(message = "Name can not be null!")
    private String name;

    @NotNull(message = "State can not be null!")
    private StylistState state = StylistState.ROOKIE;

    public StylistDTO() {
    }

    public StylistDTO(Long id, String name, StylistState state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }


    public static StylistDTO.StylistDTOBuilder newBuilder() {
        return new StylistDTO.StylistDTOBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StylistState getState() {
        return state;
    }

    public static class StylistDTOBuilder {
        private Long id;
        private String name;
        private StylistState state = StylistState.ROOKIE;


        public StylistDTO.StylistDTOBuilder setName(String name) {
            this.name = name;
            return this;

        }

        public StylistDTO.StylistDTOBuilder setState(StylistState state) {
            this.state = state;
            return this;

        }


        public StylistDTO.StylistDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }


        public StylistDTO createStylistDTO() {
            return new StylistDTO(id, name, state);
        }

    }
}
