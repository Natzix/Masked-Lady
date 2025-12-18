package org.natzi.maskedlady.utils.dto;

import jakarta.validation.constraints.NotNull;

public record AccountLoginDTO(@NotNull(message = "Dato de usuario invalido") String username) {
}
