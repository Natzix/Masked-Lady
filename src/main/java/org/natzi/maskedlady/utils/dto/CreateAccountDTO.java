package org.natzi.maskedlady.utils.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateAccountDTO(
        @NotNull(message = "Dato de usuario invalido")
        String username,

        @NotNull(message = "Es obligatorio el correo electronico")
        @Email(message = "No has ingresado tu correo electronico")
        String email,

        @NotNull
        int role
) {
}
