package com.example.veterinarinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veterinarinary.model.Pet;

public interface IPet extends JpaRepository<Pet, Integer> {
    List<Pet> findByPetNameContainingIgnoreCase(String filter);
}