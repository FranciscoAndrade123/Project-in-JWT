package com.example.veterinarinary.DTO;

import java.time.LocalDate;

public class AppointmentDTO {

    private LocalDate appointmentDate; // la fecha de la cita
    private int petID; // el id de la mascota
    private int userID; // el id del usuario (antes veterinarianID)
    private int placeID; // el id del lugar

    public AppointmentDTO() {} // Constructor vacío

    // Constructor con parámetros
    public AppointmentDTO(LocalDate appointmentDate, int petID, int userID, int placeID) {
        this.appointmentDate = appointmentDate;
        this.petID = petID;
        this.userID = userID;
        this.placeID = placeID;
    }

    // Getters y Setters
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }
}