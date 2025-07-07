package com.example.veterinarinary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.DTO.TreatmentDTO;
import com.example.veterinarinary.service.TreatmentService;

/**
 * Controlador REST para la gestión de tratamientos veterinarios.
 */
@RestController
@RequestMapping("/api/v1/treatment")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    /**
     * Endpoint para crear un tratamiento.
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> createTreatment(@RequestBody TreatmentDTO treatmentDTO) {
        ResponseDTO response = treatmentService.save(treatmentDTO);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Endpoint para obtener todos los tratamientos.
     */
    @GetMapping
    public ResponseEntity<?> getAllTreatments() {
        var treatmentList = treatmentService.findAll();
        return new ResponseEntity<>(treatmentList, HttpStatus.OK);
    }

    /**
     * Endpoint para obtener un tratamiento por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTreatmentById(@PathVariable int id) {
        var treatment = treatmentService.findById(id);
        if (treatment.isPresent()) {
            // Puedes retornar un DTO si prefieres ocultar campos internos
            return new ResponseEntity<>(treatmentService.convertToDTO(treatment.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(
            new ResponseDTO(
                "El tratamiento no existe",
                HttpStatus.NOT_FOUND.toString()
            ),
            HttpStatus.NOT_FOUND
        );
    }

    /**
     * Endpoint para eliminar un tratamiento por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteTreatment(@PathVariable int id) {
        ResponseDTO message = treatmentService.deleteTreatment(id);
        // Puedes poner HttpStatus.NOT_FOUND si el mensaje indica que no existe
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Endpoint para actualizar un tratamiento.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTreatment(@PathVariable int id, @RequestBody TreatmentDTO treatmentDTO) {
        ResponseDTO response = treatmentService.updateTreatment(id, treatmentDTO);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Endpoint para filtrar tratamientos por nombre.
     */
    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> filterTreatments(@PathVariable String filter) {
        var treatmentList = treatmentService.filterByName(filter);
        return new ResponseEntity<>(treatmentList, HttpStatus.OK);
    }
}