package com.ivanppf.desafio_klok.business.services.Cliente;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.repositories.ClienteRepository;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente buscarPorId(UUID id) {
        return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}
