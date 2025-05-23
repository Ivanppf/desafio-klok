package com.ivanppf.desafio_klok.presentation.DTO.request;

import com.ivanppf.desafio_klok.model.entities.enums.TipoCliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequestDTO(
        @NotBlank(message = "Por favor, informe um nome") String nome,
        @NotBlank(message = "Por favor, informe um email") String email,
        @NotNull(message = "Por favor, informe um tipo") TipoCliente tipo) {

}
