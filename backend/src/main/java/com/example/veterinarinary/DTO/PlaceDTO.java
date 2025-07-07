package com.example.veterinarinary.DTO;

public class PlaceDTO {
    private String placeName;

    // Constructor vacío (necesario para frameworks como Spring)
    public PlaceDTO() {}

    // Constructor con parámetros
    public PlaceDTO(String placeName) {
        this.placeName = placeName;
    }

    // Getter y Setter
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName; 
    }
}