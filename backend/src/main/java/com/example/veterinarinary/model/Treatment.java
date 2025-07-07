package com.example.veterinarinary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "treatment")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_id")
    private int treatmentId;

    @Column(name = "treatment_name", nullable = false, length = 150)
    private String treatmentName;

    @Column(name = "treatment_description", nullable = false, length = 250)
    private String treatmentDescription;

    // Constructor vacío
    public Treatment() {}

    // Constructor con parámetros
    public Treatment(String treatmentName, String treatmentDescription) {
        this.treatmentName = treatmentName;
        this.treatmentDescription = treatmentDescription;
    }

    // Getters y setters
    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }
}