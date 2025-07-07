package com.example.veterinarinary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.veterinarinary.DTO.PetDTO;
import com.example.veterinarinary.model.Breed;
import com.example.veterinarinary.model.Pet;
import com.example.veterinarinary.model.User;
import com.example.veterinarinary.repository.IBreed;
import com.example.veterinarinary.repository.IPet;
import com.example.veterinarinary.repository.IUser;

@Service
public class PetService {

    @Autowired
    private IPet petRepository;

    @Autowired
    private IUser userRepository;

    @Autowired
    private IBreed breedRepository;

    // Guardar mascota
    @Transactional
    public Pet save(PetDTO petDTO) {
        Pet petEntity = convertToModel(petDTO);
        return petRepository.save(petEntity);
    }

    // Convertir de DTO a modelo
    public Pet convertToModel(PetDTO petDTO) {
        // Buscar usuario y raza en la base de datos
        User userEntity = userRepository.findById(petDTO.getUserID())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Breed breedEntity = breedRepository.findById(petDTO.getBreedID())
            .orElseThrow(() -> new RuntimeException("Raza no encontrada"));
        return new Pet(
            petDTO.getPetName(),
            userEntity,
            breedEntity
        );
    }

    // Buscar mascota por ID
    public Optional<Pet> findById(int id) {
        return petRepository.findById(id);
    }

    // Obtener todas las mascotas
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    // Eliminar mascota por ID
    @Transactional
    public void deleteById(int id) {
        petRepository.deleteById(id);
    }

    // Verificar si una mascota existe por ID
    public boolean existsById(int id) {
        return petRepository.existsById(id);
    }

    // Actualizar mascota
    @Transactional
    public Pet updatePet(int id, PetDTO petDTO) {
        // Verificar si la mascota existe
        Pet existingPet = petRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));

        // Buscar usuario asociado
        User userEntity = userRepository.findById(petDTO.getUserID())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + petDTO.getUserID()));

        // Buscar raza asociada
        Breed breedEntity = breedRepository.findById(petDTO.getBreedID())
            .orElseThrow(() -> new RuntimeException("Raza no encontrada con ID: " + petDTO.getBreedID()));

        // Actualizar los datos de la mascota
        existingPet.setPetName(petDTO.getPetName());
        existingPet.setUser(userEntity);
        existingPet.setBreed(breedEntity);

        // Guardar los cambios
        return petRepository.save(existingPet);
    }

    // Filtrar mascotas por nombre
    public List<Pet> filterByName(String filter) {
        return petRepository.findByPetNameContainingIgnoreCase(filter);
    }
}