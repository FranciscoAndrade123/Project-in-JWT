package com.example.veterinarinary.controller;

import com.example.veterinarinary.DTO.email.ChangePasswordRequest;
import com.example.veterinarinary.DTO.email.ResetPasswordRequest;
import com.example.veterinarinary.service.PasswordService;
import com.example.veterinarinary.model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PutMapping("/change")
        public ResponseEntity<String> changePassword(
        @AuthenticationPrincipal User userDetails,
        @RequestBody ChangePasswordRequest request
            ) {
        Integer userId = userDetails.getUserID();
            passwordService.changePasswordFromProfile(userId, request.getCurrentPassword(), request.getNewPassword());
        return ResponseEntity.ok("Contraseña cambiada correctamente.");
        }

        @PostMapping("/reset")
        public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
            passwordService.resetPasswordWithToken(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Contraseña restablecida correctamente.");
}
}