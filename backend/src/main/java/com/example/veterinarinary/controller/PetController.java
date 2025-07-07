package com.example.veterinarinary.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.veterinarinary.DTO.PetDTO;
import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.model.Pet;
import com.example.veterinarinary.service.PetService;

@RestController
@RequestMapping("/api/v1/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<ResponseDTO> registerPet(@RequestBody PetDTO petDTO) {
        try{
            petService.save(petDTO);
            return new ResponseEntity<>(new ResponseDTO("Mascota registrada correctamente", "success"), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new ResponseDTO("Error: " + e.getMessage(), "error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable int id) {
        try {
            Optional<Pet> petOptional = petService.findById(id);
            if (petOptional.isPresent()) {
                return new ResponseEntity<>(petOptional.get(), HttpStatus.OK);
            } else {
                ResponseDTO response = new ResponseDTO("Mascota no encontrada", "error");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("Error al buscar mascota: " + e.getMessage(), "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPets() {
        try {
            List<Pet> pets = petService.findAll();
            return new ResponseEntity<>(pets, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("Error al obtener mascotas: " + e.getMessage(), "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePet(@PathVariable int id) {
        try {
            if (petService.existsById(id)) {
                petService.deleteById(id);
                ResponseDTO response = new ResponseDTO("Mascota eliminada correctamente", "success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseDTO response = new ResponseDTO("Mascota no encontrada", "error");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("Error al eliminar mascota: " + e.getMessage(), "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePet(@PathVariable int id, @RequestBody PetDTO petDTO) {
        try {
            if (petService.existsById(id)) {
                petService.updatePet(id, petDTO);
                ResponseDTO response = new ResponseDTO("Mascota actualizada correctamente", "success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseDTO response = new ResponseDTO("Mascota no encontrada", "error");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("Error al actualizar mascota: " + e.getMessage(), "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<?> filterPetsByName(@PathVariable String filter) {
        try {
            List<Pet> pets = petService.filterByName(filter);
            return new ResponseEntity<>(pets, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("Error al filtrar mascotas: " + e.getMessage(), "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}