package com.example.veterinarinary.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "appointment")
public class Appointment {
    // Clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentID")
    private int appointmentID;

    // Fecha de la cita
    @Column(name = "appointmentDate", nullable = false)
    private LocalDate appointmentDate;

    // Relación ManyToOne con Pet
    @ManyToOne
    @JoinColumn(name = "petID", nullable = false)
    private Pet pet;

    // Relación ManyToOne con User (anteriormente Veterinarian)
    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    // Relación ManyToOne con Place
    @ManyToOne
    @JoinColumn(name = "placeID", nullable = false)
    private Place place;

    // Estado de la cita
    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private boolean status;

    // Constructor vacío (necesario para JPA)
    public Appointment() {}

    // Constructor con parámetros
    public Appointment(LocalDate appointmentDate, Pet pet, User user, Place place, boolean status) {
        this.appointmentDate = appointmentDate;
        this.pet = pet;
        this.user = user;
        this.place = place;
        this.status = status;
    }

    // Getters y Setters
    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}