package com.example.veterinarinary.model;

public class UserSpeciality {

    private int userSpecialityID;
    private User user;
    private Specialty specialty;

    // Constructor vacío
    public UserSpeciality() {}

    // Constructor con parámetros
    public UserSpeciality(int userSpecialityID, User user, Specialty specialty) {
        this.userSpecialityID = userSpecialityID;
        this.user = user;
        this.specialty = specialty;
    }

    // Getters y Setters
    public int getUserSpecialityID() {
        return userSpecialityID;
    }

    public void setUserSpecialityID(int userSpecialityID) {
        this.userSpecialityID = userSpecialityID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}