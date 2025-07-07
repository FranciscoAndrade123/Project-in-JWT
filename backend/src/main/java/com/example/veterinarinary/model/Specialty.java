package com.example.veterinarinary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "specialty")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialtyID") // Mantén el nombre exacto si tu DB lo requiere
    private int specialtyId;

    @Column(name = "specialtyName", length = 100, nullable = false)
    private String specialtyName;

    // Constructor vacío
    public Specialty() {}

    // Constructor solo con nombre (para crear nuevos)
    public Specialty(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    // Getters y setters
    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}