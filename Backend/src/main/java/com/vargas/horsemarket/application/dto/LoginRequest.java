package com.vargas.horsemarket.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "El email es requerido")
    @Email
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;
}
