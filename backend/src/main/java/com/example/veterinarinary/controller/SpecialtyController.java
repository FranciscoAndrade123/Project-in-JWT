package com.example.veterinarinary.controller;

import java.util.List;
import java.util.Optional;

import com.example.veterinarinary.DTO.SpecialtyDTO;
import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.DTO.ResponseSpecialtyDTO;
import com.example.veterinarinary.service.SpecialtyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de especialidades veterinarias.
 */
@RestController
@RequestMapping("/api/v1/specialty")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createSpecialty(@RequestBody SpecialtyDTO specialtyDTO) {
        ResponseDTO response = specialtyService.save(specialtyDTO);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping
    public ResponseEntity<List<ResponseSpecialtyDTO>> getAllSpecialties() {
        return new ResponseEntity<>(specialtyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecialtyById(@PathVariable int id) {
        Optional<SpecialtyDTO> specialty = specialtyService.findById(id);
        return specialty.isPresent()
            ? new ResponseEntity<>(specialty.get(), HttpStatus.OK)
            : new ResponseEntity<>(new ResponseDTO("La especialidad no existe", HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<List<?>> filterSpecialtyByName(@PathVariable String filter) {
        var specialtyList = specialtyService.filterByName(filter);
        return new ResponseEntity<>(specialtyList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteSpecialty(@PathVariable int id) {
        ResponseDTO response = specialtyService.deleteSpecialty(id);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateSpecialty(@PathVariable int id, @RequestBody SpecialtyDTO specialtyDTO) {
        ResponseDTO response = specialtyService.updateSpecialty(id, specialtyDTO);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}