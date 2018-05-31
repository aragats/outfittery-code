package com.outfittery.challenge.model.params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Embeddable
public class TimeSlot {

    @Column(nullable = false, name = "from_time")
    @NotNull(message = "Timeslot 'from' can not be null!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime from;
    @Column(nullable = false, name = "to_time")
    @NotNull(message = "Timeslot 'to' can not be null!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime to;

    public TimeSlot() {
    }

    public TimeSlot(ZonedDateTime from, ZonedDateTime to) {
        this.from = from;
        this.to = to;
    }

    //    2018-01-04T18:00:00.000Z
    @JsonProperty
    public String getFrom() {
        return from.toString();
    }

    public void setFrom(String from) {
        this.from = ZonedDateTime.parse(from);
    }

    @JsonProperty
    public String getTo() {
        return to.toString();
    }

    public void setTo(String to) {
        this.to = ZonedDateTime.parse(to);
    }


    @JsonIgnore
    public void setFromTime(ZonedDateTime from) {
        this.from = from;
    }

    @JsonIgnore
    public void setToTime(ZonedDateTime to) {
        this.to = to;
    }


    @JsonIgnore
    public ZonedDateTime getToTime() {
        return to;
    }

    @JsonIgnore
    public ZonedDateTime getFromTime() {
        return from;
    }
}
