package com.example.veterinarinary.DTO;

public class UserSpecialtyDTO {

    private int id;
    private int userId;
    private int specialtyId;

    public UserSpecialtyDTO() {}

    public UserSpecialtyDTO(int id, int userId, int specialtyId) {
        this.id = id;
        this.userId = userId;
        this.specialtyId = specialtyId;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }
}