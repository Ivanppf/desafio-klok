package com.ivanppf.desafio_klok.model.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    @Column(nullable = false)
    @OneToMany
    @JoinColumn(name = "ITEM_ID")
    private List<Item> items;
    private Double total;
    private Double totalComDesconto;
    private Boolean emEstoque;
    private LocalDate dataEntrega;

    

    public Pedido(Cliente cliente, List<Item> items) {
        this.cliente = cliente;
        this.items = items;
    }
    public Pedido(Cliente cliente, List<Item> items, Double total, Double totalComDesconto, Boolean emEstoque,
            LocalDate dataEntrega) {
        this.cliente = cliente;
        this.items = items;
        this.total = total;
        this.totalComDesconto = totalComDesconto;
        this.emEstoque = emEstoque;
        this.dataEntrega = dataEntrega;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public Double getTotalComDesconto() {
        return totalComDesconto;
    }
    public void setTotalComDesconto(Double totalComDesconto) {
        this.totalComDesconto = totalComDesconto;
    }
    public Boolean getEmEstoque() {
        return emEstoque;
    }
    public void setEmEstoque(Boolean emEstoque) {
        this.emEstoque = emEstoque;
    }
    public LocalDate getDataEntrega() {
        return dataEntrega;
    }
    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    
}
