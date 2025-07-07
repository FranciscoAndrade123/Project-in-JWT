package com.example.veterinarinary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placeid")
    private int placeId; 
    
    @Column(name = "place_name", length = 50, nullable = false)
    private String placeName;

    // Constructor vacío (necesario para JPA)
    public Place() {}

    // Constructor con parámetros
    public Place(int placeId, String placeName) {
        this.placeId = placeId;
        this.placeName = placeName;
    }

    // Getters y Setters
    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}