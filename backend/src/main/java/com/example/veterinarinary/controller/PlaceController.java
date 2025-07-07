package com.example.veterinarinary.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import com.example.veterinarinary.DTO.PlaceDTO;
import com.example.veterinarinary.DTO.ResponsePlaceDTO;
import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.service.PlaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de lugares.
 */
@RestController
@RequestMapping("/api/v1/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/")
    public ResponseEntity<Object> createPlace(@RequestBody PlaceDTO placeDTO) {
        ResponseDTO response = placeService.save(placeDTO);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponsePlaceDTO>> getAllPlaces() {
        List<ResponsePlaceDTO> places = placeService.findAll();
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlaceById(@PathVariable int id) {
        Optional<PlaceDTO> place = placeService.findById(id);
        return place.isPresent()
            ? new ResponseEntity<>(place.get(), HttpStatus.OK)
            : new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "El lugar no existe"), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlace(@PathVariable int id, @RequestBody PlaceDTO placeDTO) {
        ResponseDTO response = placeService.updatePlace(id, placeDTO);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlace(@PathVariable int id) {
        ResponseDTO response = placeService.deletePlace(id);
        HttpStatus status = response.getStatus().equals(HttpStatus.OK.toString()) ? HttpStatus.OK : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> getListPlaceForName(@PathVariable String filter) {
        var placeList = placeService.filterByName(filter);
        return new ResponseEntity<>(placeList, HttpStatus.OK);
    }
}