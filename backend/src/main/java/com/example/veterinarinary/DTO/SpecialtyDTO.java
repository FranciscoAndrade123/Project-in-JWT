package com.example.veterinarinary.DTO;

public class SpecialtyDTO {

    private String specialtyName;

    public SpecialtyDTO() {}

    public SpecialtyDTO(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}