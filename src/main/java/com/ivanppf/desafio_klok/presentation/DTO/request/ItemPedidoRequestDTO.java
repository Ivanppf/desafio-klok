package com.ivanppf.desafio_klok.presentation.DTO.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequestDTO(
        @NotNull(message = "Por favor informe o id do item") UUID idItem,
        @NotNull(message = "Por favor informe o quantidade de itens") int quantidade) {
}
