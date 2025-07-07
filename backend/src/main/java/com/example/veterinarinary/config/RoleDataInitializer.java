package com.example.veterinarinary.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.veterinarinary.model.Role;
import com.example.veterinarinary.repository.IRole;

@Component
public class RoleDataInitializer implements CommandLineRunner {

    private final IRole roleRepository;

    public RoleDataInitializer(IRole roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotExists("ROLE_CLIENTE");
        createRoleIfNotExists("ROLE_VETERINARIO");
        createRoleIfNotExists("ROLE_ADMIN");
    }

    private void createRoleIfNotExists(String roleName) {
        if (!roleRepository.existsByName(roleName)) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
            System.out.println("Rol " + roleName + " creado exitosamente");
        }
    }
}