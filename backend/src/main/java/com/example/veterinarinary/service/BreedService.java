package com.example.veterinarinary.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.veterinarinary.DTO.BreedDTO;
import com.example.veterinarinary.DTO.ResponseBreedDTO;
import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.model.Breed;
import com.example.veterinarinary.repository.IBreed;

@Service
public class BreedService {

    @Autowired
    private IBreed data;

    // Guardar (Registrar y Actualizar)
    public ResponseDTO save(BreedDTO breedDTO) {
        // Validaciones antes de guardar
        if (breedDTO.getBreedName() == null || breedDTO.getBreedName().trim().isEmpty()) {
            return new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre de la raza no puede estar vacío");
        }
        if (breedDTO.getCharacteristic() == null || breedDTO.getCharacteristic().trim().isEmpty()) {
            return new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), "La característica no puede estar vacía");
        }

        // Guardar la raza en la base de datos
        Breed breedRegister = convertToModel(breedDTO);
        data.save(breedRegister);
        return new ResponseDTO(HttpStatus.OK.toString(), "Raza guardada correctamente");
    }

    // Listar todas las razas
    public List<ResponseBreedDTO> findAll() {
        List<Breed> breeds = data.findAll();
        return breeds.stream()
                     .map(this::convertToResponseDTO)
                     .collect(Collectors.toList());
    }

    // Buscar por ID
    public Optional<BreedDTO> findById(int id) {
        Optional<Breed> breed = data.findById(id);
        return breed.map(this::convertToDTO);
    }

    // Eliminar por ID
    public ResponseDTO deleteBreed(int id) {
        Optional<Breed> breed = data.findById(id);
        if (!breed.isPresent()) {
            return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "La raza no existe");
        }

        data.deleteById(id);
        return new ResponseDTO(HttpStatus.OK.toString(), "Raza eliminada correctamente");
    }

    // Actualizar por ID
    public ResponseDTO updateBreed(int id, BreedDTO breedDTO) {
        Optional<Breed> breed = data.findById(id);
        if (!breed.isPresent()) {
            return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "La raza no existe");
        }
        Breed existingBreed = breed.get();
        existingBreed.setBreedName(breedDTO.getBreedName());
        existingBreed.setCharacteristic(breedDTO.getCharacteristic());
        data.save(existingBreed);

        return new ResponseDTO(HttpStatus.OK.toString(), "Raza actualizada correctamente");
    }

    // Filtrar el nombre de la raza
    public List<Breed> filterByName(String filter){
        return data.findByBreedNameContainingIgnoreCase(filter);
    }

    // Convertir de Entidad a DTO
    public BreedDTO convertToDTO(Breed breed) {
        return new BreedDTO(
            breed.getBreedName(),
            breed.getCharacteristic()
        );
    }

    public ResponseBreedDTO convertToResponseDTO(Breed breed) {
        return new ResponseBreedDTO(
            breed.getIdBreed(),
            breed.getBreedName(),
            breed.getCharacteristic()
        );
    }

    // Convertir de DTO a Entidad
    public Breed convertToModel(BreedDTO breedDTO) {
        return new Breed(
            breedDTO.getBreedName(),
            breedDTO.getCharacteristic()
        );
    }
}