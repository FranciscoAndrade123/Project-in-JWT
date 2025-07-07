package com.example.veterinarinary.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.veterinarinary.DTO.SpecialtyDTO;
import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.DTO.ResponseSpecialtyDTO;
import com.example.veterinarinary.model.Specialty;
import com.example.veterinarinary.repository.ISpecialty;

@Service
public class SpecialtyService {

    @Autowired
    private ISpecialty data;

    // Guardar (Registrar y Actualizar)
    public ResponseDTO save(SpecialtyDTO specialtyDTO) {
        // Validación antes de guardar
        if (specialtyDTO.getSpecialtyName() == null || specialtyDTO.getSpecialtyName().trim().isEmpty()) {
            return new ResponseDTO("El nombre de la especialidad no puede estar vacío", HttpStatus.BAD_REQUEST.toString());
        }

        // Guardar en la base de datos
        Specialty specialtyRegister = convertToModel(specialtyDTO);
        data.save(specialtyRegister);
        return new ResponseDTO("Especialidad guardada correctamente", HttpStatus.OK.toString());
    }

    // Listar todas las especialidades
    public List<ResponseSpecialtyDTO> findAll() {
        List<Specialty> specialties = data.findAll();
        return specialties.stream()
                     .map(this::convertToResponseDTO)
                     .collect(Collectors.toList());
    }

    // Filtrar por el nombre de la especialidad
    public List<Specialty> filterByName(String filter) {
        return data.findBySpecialtyNameContainingIgnoreCase(filter);
    }

    // Buscar por ID
    public Optional<SpecialtyDTO> findById(int id) {
        Optional<Specialty> specialty = data.findById(id);
        return specialty.map(this::convertToDTO);
    }

    // Eliminar por ID
    public ResponseDTO deleteSpecialty(int id) {
        Optional<Specialty> specialty = data.findById(id);
        if (!specialty.isPresent()) {
            return new ResponseDTO("La especialidad no existe", HttpStatus.NOT_FOUND.toString());
        }
        data.deleteById(id);
        return new ResponseDTO("Especialidad eliminada correctamente", HttpStatus.OK.toString());
    }

    // Actualizar
    public ResponseDTO updateSpecialty(int id, SpecialtyDTO specialtyDTO) {
        Optional<Specialty> specialty = data.findById(id);
        if (!specialty.isPresent()) {
            return new ResponseDTO("La especialidad no existe", HttpStatus.NOT_FOUND.toString());
        }
        Specialty existingSpecialty = specialty.get();
        existingSpecialty.setSpecialtyName(specialtyDTO.getSpecialtyName());

        data.save(existingSpecialty);

        return new ResponseDTO("Especialidad actualizada correctamente", HttpStatus.OK.toString());
    }

    // Convertir de Entidad a DTO
    public SpecialtyDTO convertToDTO(Specialty specialty) {
        return new SpecialtyDTO(specialty.getSpecialtyName());
    }

    public ResponseSpecialtyDTO convertToResponseDTO(Specialty specialty) {
        return new ResponseSpecialtyDTO(specialty.getSpecialtyId(), specialty.getSpecialtyName());
    }

    // Convertir de DTO a Entidad
    public Specialty convertToModel(SpecialtyDTO specialtyDTO) {
        return new Specialty(
            specialtyDTO.getSpecialtyName()
        );
    }
}