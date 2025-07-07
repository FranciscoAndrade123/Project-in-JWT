package com.example.veterinarinary.DTO;

public class ResponseBreedDTO {
    private int id;
    private String breedName;
    private String characteristic;

    public ResponseBreedDTO() {
    }

    public ResponseBreedDTO(int id, String breedName, String characteristic) {
        this.id = id;
        this.breedName = breedName;
        this.characteristic = characteristic;
    }

    // Getter y Setter para breedName

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
}