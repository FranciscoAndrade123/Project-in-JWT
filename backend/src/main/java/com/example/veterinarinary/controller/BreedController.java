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

import com.example.veterinarinary.DTO.BreedDTO;
import com.example.veterinarinary.DTO.ResponseBreedDTO;
import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.service.BreedService;

/**
 * Controlador REST para la gestión de razas.
 */
@RestController
@RequestMapping("/api/v1/breed")
public class BreedController {

    @Autowired
    private BreedService breedService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createBreed(@RequestBody BreedDTO breedDTO) {
        ResponseDTO response = breedService.save(breedDTO);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping
    public ResponseEntity<List<ResponseBreedDTO>> getAllBreeds() {
        return new ResponseEntity<>(breedService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBreedById(@PathVariable int id) {
        Optional<BreedDTO> breed = breedService.findById(id);
        return breed.isPresent()
            ? new ResponseEntity<>(breed.get(), HttpStatus.OK)
            : new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "La raza no existe"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteBreed(@PathVariable int id) {
        ResponseDTO response = breedService.deleteBreed(id);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateBreed(@PathVariable int id, @RequestBody BreedDTO breedDTO) {
        ResponseDTO response = breedService.updateBreed(id, breedDTO);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<List<?>> filterByName(@PathVariable String filter) {
        var breedList = breedService.filterByName(filter);
        return new ResponseEntity<>(breedList, HttpStatus.OK);
    }
}