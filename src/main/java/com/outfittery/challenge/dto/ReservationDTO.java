package com.outfittery.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.outfittery.challenge.model.params.TimeSlot;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDTO {


    @JsonIgnore
    private Long id;

    @NotNull(message = "Comment can not be null!")
    private String comment;

    @NotNull(message = "Timeslot can not be null!")
    private TimeSlot timeslot;


    @NotNull(message = "Customer can not be null!")
    private String customer;


    public ReservationDTO() {
    }

    public ReservationDTO(Long id, String comment, TimeSlot timeslot, String customer) {
        this.id = id;
        this.comment = comment;
        this.timeslot = timeslot;
        this.customer = customer;
    }

    public static ReservationDTOBuilder newBuilder() {
        return new ReservationDTO.ReservationDTOBuilder();
    }


    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public String getComment() {
        return comment;
    }

    @JsonProperty
    public TimeSlot getTimeslot() {
        return timeslot;
    }

    @JsonProperty
    public String getCustomer() {
        return customer;
    }

    public static class ReservationDTOBuilder {
        private Long id;

        private String comment;

        private TimeSlot timeslot;


        private String customer;


        public ReservationDTO.ReservationDTOBuilder setComment(String comment) {
            this.comment = comment;
            return this;

        }

        public ReservationDTO.ReservationDTOBuilder setTimeslot(TimeSlot timeslot) {
            this.timeslot = timeslot;
            return this;

        }

        public ReservationDTO.ReservationDTOBuilder setCustomer(String customer) {
            this.customer = customer;
            return this;

        }

        public ReservationDTO.ReservationDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }


        public ReservationDTO createModelDTO() {
            return new ReservationDTO(id, comment, timeslot, customer);
        }

    }
}
