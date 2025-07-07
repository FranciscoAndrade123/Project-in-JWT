package com.example.veterinarinary.DTO;

public class PetDTO {

    private String petName;
    private int userID;  // Solo el ID del usuario (antes clientID)
    private int breedID; // Solo el ID de la raza

    // Constructor vacío (necesario para frameworks como Spring)
    public PetDTO() {}

    // Constructor con parámetros
    public PetDTO(String petName, int userID, int breedID) {
        this.petName = petName;
        this.userID = userID;
        this.breedID = breedID;
    }

    // Getters y Setters
    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBreedID() {
        return breedID;
    }

    public void setBreedID(int breedID) {
        this.breedID = breedID;
    }
}