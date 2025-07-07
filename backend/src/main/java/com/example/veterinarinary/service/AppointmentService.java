package com.example.veterinarinary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.veterinarinary.DTO.AppointmentDTO;
import com.example.veterinarinary.DTO.ResponseDTO;
import com.example.veterinarinary.model.Pet;
import com.example.veterinarinary.model.User;
import com.example.veterinarinary.model.Place;
import com.example.veterinarinary.model.Appointment;
import com.example.veterinarinary.repository.IAppointment;
import com.example.veterinarinary.repository.IPlace;
import com.example.veterinarinary.repository.IPet;
import com.example.veterinarinary.repository.IUser;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    
    @Autowired
    private IAppointment appointmentRepository;
    
    @Autowired
    private IPlace placeRepository;
    
    @Autowired
    private IPet petRepository;
    
    @Autowired
    private IUser userRepository;
    
    // Guardar cita con validaciones
    @Transactional
    public ResponseDTO save(AppointmentDTO appointmentDTO) {
        Optional<Place> placeEntity = placeRepository.findById(appointmentDTO.getPlaceID());
        if (!placeEntity.isPresent()) {
            return new ResponseDTO("Lugar no encontrado", "error");
        }
        
        Optional<Pet> petEntity = petRepository.findById(appointmentDTO.getPetID());
        if (!petEntity.isPresent()) {
            return new ResponseDTO("Mascota no encontrada", "error");
        }
        
        Optional<User> userEntity = userRepository.findById(appointmentDTO.getUserID());
        if (!userEntity.isPresent()) {
            return new ResponseDTO("Usuario no encontrado", "error");
        }
        
        Appointment appointmentEntity = new Appointment();
        appointmentEntity.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointmentEntity.setPlace(placeEntity.get());
        appointmentEntity.setPet(petEntity.get());
        appointmentEntity.setUser(userEntity.get());
        appointmentEntity.setStatus(true); // Asignar estado activo por defecto
        
        appointmentRepository.save(appointmentEntity);
        
        return new ResponseDTO("Cita registrada correctamente", "success");
    }
    
    // Buscar cita por ID
    public Optional<Appointment> findById(int id) {
        return appointmentRepository.findById(id);
    }
    
    // Listar todas las citas activas
    public List<Appointment> findAll() {
        return appointmentRepository.getListAppointmenttActive();
    }
    
    // Verificar si una cita existe
    public boolean existsById(int id) {
        return appointmentRepository.existsById(id);
    }
    
    // Eliminar cita por ID (desactivar)
    @Transactional
    public void deleteById(int id) {
        appointmentRepository.deactivateAppointment(id);
    }

    // Buscar las citas activas o inactivas
    public List<Appointment> findByStatus(boolean status) {
         return appointmentRepository.findByStatus(status);
    }

    // Filtrar citas por el nombre de la mascota 
    public List<Appointment> findByPetName(String petName) {
        return appointmentRepository.findByPetName(petName);
    }

    // Actualizar los datos de la entidad cita 
    @Transactional
    public ResponseDTO updateAppointment(int id, AppointmentDTO appointmentDTO) {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(id);
      
        if (!existingAppointment.isPresent()) {
            return new ResponseDTO("Cita con ID " + id + " no encontrada", "error");
        }
      
        Optional<Place> placeEntity = placeRepository.findById(appointmentDTO.getPlaceID());
        if (!placeEntity.isPresent()) {
            return new ResponseDTO("Lugar no encontrado", "error");
        }
      
        Optional<Pet> petEntity = petRepository.findById(appointmentDTO.getPetID());
        if (!petEntity.isPresent()) {
            return new ResponseDTO("Mascota no encontrada", "error");
        }
      
        Optional<User> userEntity = userRepository.findById(appointmentDTO.getUserID());
        if (!userEntity.isPresent()) {
            return new ResponseDTO("Usuario no encontrado", "error");
        }
      
        // Actualizar los datos de la cita existente
        Appointment appointmentEntity = existingAppointment.get();
        appointmentEntity.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointmentEntity.setPlace(placeEntity.get());
        appointmentEntity.setPet(petEntity.get());
        appointmentEntity.setUser(userEntity.get());
      
        appointmentRepository.save(appointmentEntity);
      
        return new ResponseDTO("Cita actualizada correctamente", "success");
    }

}