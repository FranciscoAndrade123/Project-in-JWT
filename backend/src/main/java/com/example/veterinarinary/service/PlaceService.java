package com.example.veterinarinary.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.veterinarinary.DTO.PlaceDTO;
import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.DTO.ResponsePlaceDTO;
import com.example.veterinarinary.model.Place;
import com.example.veterinarinary.repository.IPlace;

@Service
public class PlaceService {

    @Autowired
    private IPlace data;

    // Guardar (Registrar y Actualizar)
    public ResponseDTO save(PlaceDTO placeDTO) {
        // Validación antes de guardar
        if (placeDTO.getPlaceName() == null || placeDTO.getPlaceName().trim().isEmpty()) {
            return new ResponseDTO("El nombre del lugar no puede estar vacío", HttpStatus.BAD_REQUEST.toString());
        }

        // Guardar en la base de datos
        Place placeRegister = convertToModel(placeDTO);
        data.save(placeRegister);
        return new ResponseDTO("Lugar guardado correctamente", HttpStatus.OK.toString());
    }

    // Listar todos los lugares
    public List<ResponsePlaceDTO> findAll() {
        List<Place> places = data.findAll();
        return places.stream()
                     .map(this::convertToResponseDTO)
                     .collect(Collectors.toList());
    }

    // Buscar por ID
    public Optional<PlaceDTO> findById(int id) {
        Optional<Place> place = data.findById(id);
        return place.map(this::convertToDTO);
    }

    // Eliminar por ID
    public ResponseDTO deletePlace(int id) {
        Optional<Place> place = data.findById(id);
        if (!place.isPresent()) {
            return new ResponseDTO("El lugar no existe", HttpStatus.NOT_FOUND.toString());
        }

        data.deleteById(id);
        return new ResponseDTO("Lugar eliminado correctamente", HttpStatus.OK.toString());
    }

    // Actualizar por ID
    public ResponseDTO updatePlace(int id, PlaceDTO placeDTO) {
        Optional<Place> place = data.findById(id);
        if (!place.isPresent()) {
            return new ResponseDTO("El lugar no existe", HttpStatus.NOT_FOUND.toString());
        }
        Place existingPlace = place.get();
        existingPlace.setPlaceName(placeDTO.getPlaceName());
        data.save(existingPlace);

        return new ResponseDTO("Lugar actualizado correctamente", HttpStatus.OK.toString());
    }

    // Filtrar por nombre
    public List<Place> filterByName(String filter) {
        // Usa el método derivado correcto del repositorio
        return data.findByPlaceNameContainingIgnoreCase(filter);
    }

    // Convertir de Entidad a DTO
    public PlaceDTO convertToDTO(Place place) {
        return new PlaceDTO(place.getPlaceName());
    }

    public ResponsePlaceDTO convertToResponseDTO(Place place) {
        return new ResponsePlaceDTO(
            place.getPlaceId(),
            place.getPlaceName()
        );
    }

    // Convertir de DTO a Entidad
    public Place convertToModel(PlaceDTO placeDTO) {
        return new Place(
            0,
            placeDTO.getPlaceName()
        );
    }
}