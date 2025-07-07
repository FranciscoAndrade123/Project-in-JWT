package com.example.veterinarinary.DTO;

public class AppointmentTreatmentDTO {

    private int id;
    private int appointmentID;
    private int treatmentID;

    // Constructor vacío
    public AppointmentTreatmentDTO() {}

    // Constructor con parámetros
    public AppointmentTreatmentDTO(int id, int appointmentID, int treatmentID) {
        this.id = id;
        this.appointmentID = appointmentID;
        this.treatmentID = treatmentID;
    }

    // Setters y Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getTreatmentID() {
        return treatmentID;
    }

    public void setTreatmentID(int treatmentID) {
        this.treatmentID = treatmentID;
    }
}