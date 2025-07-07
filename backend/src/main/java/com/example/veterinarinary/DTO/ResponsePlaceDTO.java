package com.example.veterinarinary.DTO;

public class ResponsePlaceDTO {

    private int id;
    private String placeName;

    public ResponsePlaceDTO() {
    }

    public ResponsePlaceDTO(int id, String placeName) {
        this.id = id;
        this.placeName = placeName;
    }

    // Getter y Setter para placeName
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}