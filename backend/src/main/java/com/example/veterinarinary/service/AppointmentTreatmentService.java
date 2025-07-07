package com.example.veterinarinary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.veterinarinary.DTO.AppointmentTreatmentDTO;
import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.model.Appointment;
import com.example.veterinarinary.model.Treatment;
import com.example.veterinarinary.model.AppointmentTreatment;
import com.example.veterinarinary.repository.IAppointmentTreatment;
import com.example.veterinarinary.repository.IAppointment;
import com.example.veterinarinary.repository.ITreatment;


@Service
public class AppointmentTreatmentService {

    @Autowired
    private IAppointmentTreatment appointmentTreatmentRepository;

    @Autowired
    private IAppointment appointmentRepository;

    @Autowired
    private ITreatment treatmentRepository;

    // Guardar relación cita-tratamiento con validaciones
    @Transactional
    public ResponseDTO save(AppointmentTreatmentDTO dto) {
        try {
            // 1. Validar existencia de cita y tratamiento
            Appointment appointment = appointmentRepository.findById(dto.getAppointmentID())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + dto.getAppointmentID()));

            Treatment treatment = treatmentRepository.findById(dto.getTreatmentID())
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado con ID: " + dto.getTreatmentID()));

            // 2. Crear y guardar la relación
            AppointmentTreatment relation = new AppointmentTreatment();
            relation.setAppointmentID(appointment);
            relation.setTreatmentId(treatment);
            appointmentTreatmentRepository.save(relation);

            // Retornar respuesta exitosa
            return new ResponseDTO(
                HttpStatus.OK.value() + "",
                "Relación cita-tratamiento creada correctamente"
            );

        } catch (Exception e) {
            // Retornar respuesta de error
            return new ResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                "Error al guardar: " + e.getMessage()
            );
        }
    }

    // Obtener todas las relaciones cita-tratamiento
    @Transactional
    public List<AppointmentTreatmentDTO> getAll() {
        List<AppointmentTreatment> relations = appointmentTreatmentRepository.findAll();
        return relations.stream().map(relation -> {
            AppointmentTreatmentDTO dto = new AppointmentTreatmentDTO();
            dto.setAppointmentID(relation.getAppointmentID().getAppointmentID());
            dto.setTreatmentID(relation.getTreatmentId().getTreatmentId());
            return dto;
        }).toList();
    }

    //Eliminar relación por ID
    @Transactional
    public ResponseDTO deleteById(int id) {
        try {
            AppointmentTreatment relation = appointmentTreatmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación no encontrada con ID: " + id));
            appointmentTreatmentRepository.delete(relation);
            return new ResponseDTO(
                HttpStatus.OK.value() + "",
                "Relación eliminada correctamente"
            );
        } catch (Exception e) {
            return new ResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                "Error al eliminar: " + e.getMessage()
            );
        }
    }

    // Actualizar relación por ID
    @Transactional
    public ResponseDTO updateById(int id, AppointmentTreatmentDTO dto) {
        try {
            // Validar si la relación existe
            AppointmentTreatment existingRelation = appointmentTreatmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación no encontrada con ID: " + id));

            // Validar existencia de la cita
            Appointment appointment = appointmentRepository.findById(dto.getAppointmentID())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + dto.getAppointmentID()));

            // Validar existencia del tratamiento
            Treatment treatment = treatmentRepository.findById(dto.getTreatmentID())
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado con ID: " + dto.getTreatmentID()));

            // Actualizar la relación
            existingRelation.setAppointmentID(appointment);
            existingRelation.setTreatmentId(treatment);

            // Guardar los cambios
            appointmentTreatmentRepository.save(existingRelation);

            return new ResponseDTO(
                HttpStatus.OK.value() + "",
                "Relación actualizada correctamente"
            );

        } catch (Exception e) {
            return new ResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                "Error al actualizar: " + e.getMessage()
            );
        }
    }
}