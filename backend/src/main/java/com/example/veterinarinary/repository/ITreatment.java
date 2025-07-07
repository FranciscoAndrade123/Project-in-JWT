package com.example.veterinarinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veterinarinary.model.Treatment;

public interface ITreatment extends JpaRepository<Treatment, Integer> {
    List<Treatment> findByTreatmentNameContainingIgnoreCase(String filter);
}