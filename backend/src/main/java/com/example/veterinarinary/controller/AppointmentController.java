package com.example.veterinarinary.controller;

import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.DTO.AppointmentDTO;
import com.example.veterinarinary.service.AppointmentService;
import com.example.veterinarinary.model.Appointment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> registerAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            ResponseDTO response = appointmentService.save(appointmentDTO);

            if (response.getStatus().equalsIgnoreCase("success")) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("Error: " + e.getMessage(), "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable int id) {
        try {
            Optional<Appointment> appointmentOptional = appointmentService.findById(id);

            if (appointmentOptional.isPresent()) {
                return new ResponseEntity<>(appointmentOptional.get(), HttpStatus.OK);
            } else {
                ResponseDTO response = new ResponseDTO("Cita con ID " + id + " no encontrada", "error");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("Error al buscar cita: " + e.getMessage(), "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Listar todas las citas
    @GetMapping("/")
    public ResponseEntity<?> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.findAll();
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("Error al obtener citas: " + e.getMessage(), "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una cita por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteAppointment(@PathVariable int id) {
        try {
            if (appointmentService.existsById(id)) {
                appointmentService.deleteById(id);
                ResponseDTO response = new ResponseDTO("Cita eliminada correctamente", "success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseDTO response = new ResponseDTO("Cita con ID " + id + " no encontrada", "error");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("Error al eliminar cita: " + e.getMessage(), "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar el estado de las citas que estén activas e inactivas
    @GetMapping("/filter/{status}")
    public ResponseEntity<List<Appointment>> filterByStatus(@PathVariable String status) {
        boolean isActive = status.equalsIgnoreCase("activo");
        List<Appointment> appointments = appointmentService.findByStatus(isActive);
        return ResponseEntity.ok(appointments);
    }

    // Filtrar por el nombre de la mascota de la cita
    @GetMapping("/filterPetName/{petName}")
    public ResponseEntity<List<Appointment>> filterByPetName(@PathVariable String petName) {
        List<Appointment> appointments = appointmentService.findByPetName(petName);
        return ResponseEntity.ok(appointments);
    }

    // Actualizar la cita
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateAppointment(@PathVariable int id, @RequestBody AppointmentDTO appointmentDTO) {
        ResponseDTO response = appointmentService.updateAppointment(id, appointmentDTO);
        if (response.getStatus().equalsIgnoreCase("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}