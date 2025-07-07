package com.example.veterinarinary.DTO;

public class ResponseDTO {
    private String message;
    private String status;

    public ResponseDTO(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public ResponseDTO() {}

    // El constructor relacionado con VeterinarianSpecialty ha sido eliminado.
    // Ahora la lógica de asignar especialidad debe hacerse con UserSpecialty 
    // en el caso de que el rol sea veterinario (esto se maneja fuera de este DTO).

    // Getters y setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}