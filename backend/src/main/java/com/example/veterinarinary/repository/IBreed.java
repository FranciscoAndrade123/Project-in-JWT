package com.example.veterinarinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veterinarinary.model.Breed;

public interface IBreed extends JpaRepository<Breed, Integer> {
    List<Breed> findByBreedNameContainingIgnoreCase(String filter);
}