package com.example.veterinarinary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.veterinarinary.model.Place;

public interface IPlace extends JpaRepository<Place, Integer> {
    // Buscar por nombre (filtro)
    List<Place> findByPlaceNameContainingIgnoreCase(String name);
}