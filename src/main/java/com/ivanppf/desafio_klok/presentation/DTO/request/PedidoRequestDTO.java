package com.ivanppf.desafio_klok.presentation.DTO.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record PedidoRequestDTO(
        @NotNull(message = "Por favor informe o cliente") UUID idCliente,
        @NotNull(message = "Por favor informe os itens do pedido") List<ItemPedidoRequestDTO> itens) {

}
