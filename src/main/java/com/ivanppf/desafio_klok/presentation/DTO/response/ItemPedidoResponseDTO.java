package com.ivanppf.desafio_klok.presentation.DTO.response;

import java.util.UUID;

public record ItemPedidoResponseDTO(
        UUID id,
        String nome,
        double preco) {

}
