package com.example.veterinarinary.DTO;

import com.example.veterinarinary.model.User;

public class RecoveryRequestDTO {
    private User userID;

    // Constructor sin parámetros
    public RecoveryRequestDTO() {
    }

    // Constructor con parámetros
    public RecoveryRequestDTO(User userID) {
        this.userID = userID;
    }

    // Getter y setter
    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }
}