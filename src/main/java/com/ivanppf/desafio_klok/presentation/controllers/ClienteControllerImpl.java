package com.ivanppf.desafio_klok.presentation.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanppf.desafio_klok.business.services.Cliente.ClienteService;
import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.presentation.DTO.request.ClienteRequestDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteControllerImpl {

    private final ClienteService clienteService;

    public ClienteControllerImpl(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos() {
        List<Cliente> clientes = clienteService.buscarTodos();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody @Valid ClienteRequestDTO obj) {
        Cliente cliente = new Cliente(obj.nome(), obj.email(), obj.tipo());
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
    
}
