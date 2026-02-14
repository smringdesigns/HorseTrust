package com.vargas.horsemarket.application.dto;

import com.vargas.horsemarket.domain.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {

    @NotBlank(message = "El username es requerido")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "El email es requerido")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotBlank(message = "El nombre es requerido")
    private String firstName;

    @NotBlank(message = "El apellido es requerido")
    private String lastName;

    private String phone;
    private String location;

    @NotEmpty(message = "Se requiere al menos un rol")
    private Set<UserRole> roles;
}
