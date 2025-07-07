package com.example.veterinarinary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "breed")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_breed")
    private int idBreed;

    @Column(name = "breed_name", nullable = false, length = 50)
    private String breedName;

    @Column(name = "characteristic", nullable = false, length = 150)
    private String characteristic;

    // Constructor vacío
    public Breed() {}

    // Constructor con parámetros
    public Breed(String breedName, String characteristic) {
        this.breedName = breedName;
        this.characteristic = characteristic;
    }

    // Getters y setters
    public int getIdBreed() {
        return idBreed;
    }

    public void setIdBreed(int idBreed) {
        this.idBreed = idBreed;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
}