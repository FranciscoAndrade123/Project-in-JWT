package com.example.veterinarinary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.veterinarinary.model.Appointment;

public interface IAppointment extends JpaRepository<Appointment, Integer> {
    /*
     * C
     * R
     * U
     * D
     */

    // Esta función es para obtener todas las citas que estén activas 
    @Query("SELECT a FROM Appointment a WHERE a.status = true")
    List<Appointment> getListAppointmenttActive();

    // Para filtrar el estatus
    @Query("SELECT app FROM Appointment app WHERE app.status = :status")
    List<Appointment> findByStatus(@Param("status") boolean status);

    // Cambia el estado de la cita a inactivo
    @Modifying
    @Query("UPDATE Appointment app SET app.status = false WHERE app.id = :id")
    void deactivateAppointment(@Param("id") int id);

    // Filtración por el nombre de la mascota
    @Query("SELECT app FROM Appointment app " +
           "JOIN app.pet p " +
           "WHERE p.petName LIKE %:petName%")
    List<Appointment> findByPetName(@Param("petName") String petName);
}