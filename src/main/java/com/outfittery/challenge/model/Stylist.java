package com.outfittery.challenge.model;

import com.outfittery.challenge.model.params.StylistState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stylist")
public class Stylist {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Name can not be null!")
    private String name;

    @NotNull(message = "State can not be null!")
    @Enumerated(EnumType.STRING)
    private StylistState state = StylistState.ROOKIE;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stylist")
    private List<Reservation> reservations = new ArrayList<>();


    public Stylist() {
    }

    public Stylist(String name, StylistState state) {
        this.name = name;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StylistState getState() {
        return state;
    }

    public void setState(StylistState state) {
        this.state = state;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
