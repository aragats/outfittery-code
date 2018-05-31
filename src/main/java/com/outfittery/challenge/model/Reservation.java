package com.outfittery.challenge.model;

import com.outfittery.challenge.model.params.TimeSlot;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @NotNull(message = "Comment can not be null!")
    private String comment;


//    @Column(nullable = false, name = "from_time")
//    @NotNull(message = "Timeslot 'from' can not be null!")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    private ZonedDateTime from;
//    @Column(nullable = false, name = "to_time")
//    @NotNull(message = "Timeslot 'to' can not be null!")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    private ZonedDateTime to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stylist_id", nullable = false)
    private Stylist stylist;

    @Embedded
    private TimeSlot timeslot;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", nullable = false)
    private String customer;


    public Reservation() {
    }

    public Reservation(String comment, TimeSlot timeslot, String customer) {
        this.comment = comment;
        this.timeslot = timeslot;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public ZonedDateTime getFrom() {
//        return from;
//    }
//
//    public void setFrom(ZonedDateTime from) {
//        this.from = from;
//    }
//
//    public ZonedDateTime getTo() {
//        return to;
//    }
//
//    public void setTo(ZonedDateTime to) {
//        this.to = to;
//    }

    public Stylist getStylist() {
        return stylist;
    }

    public void setStylist(Stylist stylist) {
        this.stylist = stylist;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
