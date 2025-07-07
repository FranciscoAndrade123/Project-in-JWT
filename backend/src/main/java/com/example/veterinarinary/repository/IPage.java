package com.example.veterinarinary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.veterinarinary.model.Page;

public interface IPage extends JpaRepository<Page, Integer> {
    boolean existsByName(String name);
}