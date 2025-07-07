package com.example.veterinarinary.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.veterinarinary.service.AppointmentTreatmentService;
import com.example.veterinarinary.DTO.AppointmentTreatmentDTO;
import com.example.veterinarinary.DTO.ResponseDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointmentTreatment")
public class AppointmentTreatmentController {

    @Autowired
    private AppointmentTreatmentService appointmentTreatmentService;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> registerAppointmentTreatment(@RequestBody AppointmentTreatmentDTO appointmentTreatmentDTO) {
        try {
            ResponseDTO response = appointmentTreatmentService.save(appointmentTreatmentDTO);

            // Convertir el código de estado a HttpStatus
            HttpStatus status = HttpStatus.resolve(Integer.parseInt(response.getMessage()));
            if (status == null) {
                status = HttpStatus.INTERNAL_SERVER_ERROR; // Valor predeterminado si el código no es válido
            }

            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Error: " + e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<AppointmentTreatmentDTO>> getAllAppointmentTreatment() {
        List<AppointmentTreatmentDTO> list = appointmentTreatmentService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Eliminar relación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        ResponseDTO response = appointmentTreatmentService.deleteById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Actualizar relación por ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable int id, @RequestBody AppointmentTreatmentDTO appointmentTreatmentDTO) {
        ResponseDTO response = appointmentTreatmentService.updateById(id, appointmentTreatmentDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}