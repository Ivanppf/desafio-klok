package com.ivanppf.desafio_klok.presentation.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRequestDTO(
    @NotBlank(message = "Por favor informe o nome do item") String nome,
    @NotNull(message = "Por favor informe o preço do item") double preco,
    @NotNull(message = "Por favor informe o quantidade do estoque do item") int estoque
) {
    
}
