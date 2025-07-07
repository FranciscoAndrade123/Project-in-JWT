package com.example.veterinarinary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "appointmentTreatment")
public class AppointmentTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private int id;

    @ManyToOne
    @JoinColumn(name = "appointmentID", nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "treatmentId", nullable = false)
    private Treatment treatment;

    // Constructor vacío
    public AppointmentTreatment() {}

    // Constructor con parámetros
    public AppointmentTreatment(int id, Appointment appointment, Treatment treatment) {
        this.id = id;
        this.appointment = appointment;
        this.treatment = treatment;
    }

    // Getters y Setters estándar
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Treatment getTreatment() {
        return this.treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    // Métodos requeridos por el servicio (alias/wrappers)
    public void setAppointmentID(Appointment appointment) {
        this.appointment = appointment;
    }

    public Appointment getAppointmentID() {
        return this.appointment;
    }

    public void setTreatmentId(Treatment treatment) {
        this.treatment = treatment;
    }

    public Treatment getTreatmentId() {
        return this.treatment;
    }
}