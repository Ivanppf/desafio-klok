package com.ivanppf.desafio_klok.model.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanppf.desafio_klok.model.entities.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    
}
