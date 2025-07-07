package com.example.veterinarinary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.DTO.TreatmentDTO;
import com.example.veterinarinary.model.Treatment;
import com.example.veterinarinary.repository.ITreatment;

@Service
public class TreatmentService {

    @Autowired
    private ITreatment data;

    // Método para guardar (Registrar y Actualizar) con validaciones
    public ResponseDTO save(TreatmentDTO treatmentDTO) {
        // Validación de nombre (no vacío y longitud máxima)
        if (treatmentDTO.getTreatmentName() == null ||
                treatmentDTO.getTreatmentName().trim().isEmpty() ||
                treatmentDTO.getTreatmentName().length() > 100) {
            return new ResponseDTO(
                "El nombre del tratamiento no puede estar vacío y debe tener máximo 100 caracteres",
                HttpStatus.BAD_REQUEST.toString()
            );
        }

        // Validación de descripción (no vacía y longitud máxima)
        if (treatmentDTO.getTreatmentDescription() == null ||
                treatmentDTO.getTreatmentDescription().trim().isEmpty() ||
                treatmentDTO.getTreatmentDescription().length() > 500) {
            return new ResponseDTO(
                "La descripción del tratamiento no puede estar vacía y debe tener máximo 500 caracteres",
                HttpStatus.BAD_REQUEST.toString()
            );
        }

        // Si las validaciones pasan, proceder a guardar
        Treatment treatmentEntity = convertToModel(treatmentDTO);
        data.save(treatmentEntity);

        // Respuesta exitosa
        return new ResponseDTO(
            "Tratamiento guardado correctamente",
            HttpStatus.OK.toString()
        );
    }

    // Convertir de Entidad a DTO
    public TreatmentDTO convertToDTO(Treatment treatment) {
        return new TreatmentDTO(
            treatment.getTreatmentName(),
            treatment.getTreatmentDescription()
        );
    }

    // Convertir de DTO a Entidad
    public Treatment convertToModel(TreatmentDTO treatmentDTO) {
    return new Treatment(
        treatmentDTO.getTreatmentName(),
        treatmentDTO.getTreatmentDescription()
    );
}

    // Buscar todos los tratamientos
    public List<Treatment> findAll() {
        return data.findAll();
    }

    // Buscar un tratamiento por ID
    public Optional<Treatment> findById(int id) {
        return data.findById(id);
    }

    // Eliminar un tratamiento por ID
    public ResponseDTO deleteTreatment(int id) {
        if (!findById(id).isPresent()) {
            return new ResponseDTO(
                "El registro no existe",
                HttpStatus.OK.toString()
            );
        }
        data.deleteById(id);
        return new ResponseDTO(
            "Tratamiento eliminado correctamente",
            HttpStatus.OK.toString()
        );
    }

    // Actualizar el tratamiento mediante el ID
    public ResponseDTO updateTreatment(int id, TreatmentDTO treatmentDTO) {
        Optional<Treatment> treatment = findById(id);
        if (!treatment.isPresent()) {
            return new ResponseDTO("El tratamiento no existe", HttpStatus.NOT_FOUND.toString());
        }

        Treatment existingTreatment = treatment.get();
        existingTreatment.setTreatmentName(treatmentDTO.getTreatmentName());
        existingTreatment.setTreatmentDescription(treatmentDTO.getTreatmentDescription());

        data.save(existingTreatment);

        return new ResponseDTO(
            "Tratamiento actualizado correctamente",
            HttpStatus.OK.toString()
        );
    }

    // Filtrar tratamientos por nombre
    public List<Treatment> filterByName(String filter) {
        // Usa el método derivado de Spring Data JPA
        return data.findByTreatmentNameContainingIgnoreCase(filter);
    }
}