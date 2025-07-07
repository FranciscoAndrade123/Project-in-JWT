package com.example.veterinarinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.veterinarinary.model.Specialty;

public interface ISpecialty extends JpaRepository<Specialty, Integer> {
    List<Specialty> findBySpecialtyNameContainingIgnoreCase(String filter);
}