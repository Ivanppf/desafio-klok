package com.ivanppf.desafio_klok.presentation.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanppf.desafio_klok.business.services.Pedido.PedidoService;
import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.presentation.DTO.request.PedidoRequestDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoControllerImpl implements PedidoController {

    private final PedidoService pedidoService;

    public PedidoControllerImpl(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Pedido>> buscarTodos() {
        List<Pedido> pedidos = pedidoService.buscarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @Override
    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody @Valid PedidoRequestDTO obj) {
        Pedido pedido = pedidoService.salvar(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

}
