package com.ivanppf.desafio_klok.model.entities;

import java.util.UUID;

import com.ivanppf.desafio_klok.model.entities.enums.TipoCliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CLIENTE_ID")
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @Email
    private String email;
    private TipoCliente tipo;

    public Cliente() {
    }

    public Cliente(String nome, String email, TipoCliente tipo) {
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    
}
