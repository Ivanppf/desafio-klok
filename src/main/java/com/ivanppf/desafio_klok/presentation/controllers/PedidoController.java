package com.ivanppf.desafio_klok.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.presentation.DTO.request.PedidoRequestDTO;

public interface PedidoController {

    ResponseEntity<List<Pedido>> buscarTodos();

    ResponseEntity<Pedido> salvar(PedidoRequestDTO obj);

}