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
    @OneToOne
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    private Cliente cliente;
    @JoinColumn(name = "ITEM_ID", nullable = false)
    @OneToMany
    private List<Item> itens;
    private Double total;
    private Double totalComDesconto;
    private Boolean emEstoque;
    private LocalDate dataEntrega;

    public Pedido(Cliente cliente, List<Item> itens) {
        this.cliente = cliente;
        this.itens = itens;
    }

    public Pedido(Cliente cliente, List<Item> itens, Double total, Double totalComDesconto, Boolean emEstoque,
            LocalDate dataEntrega) {
        this.cliente = cliente;
        this.itens = itens;
        this.total = total;
        this.totalComDesconto = totalComDesconto;
        this.emEstoque = emEstoque;
        this.dataEntrega = dataEntrega;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        result = prime * result + ((itens == null) ? 0 : itens.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (cliente == null) {
            if (other.cliente != null)
                return false;
        } else if (!cliente.equals(other.cliente))
            return false;
        if (itens == null) {
            if (other.itens != null)
                return false;
        } else if (!itens.equals(other.itens))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        return true;
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

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
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

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", cliente=" + cliente + ", itens=" + itens + ", total=" + total
                + ", totalComDesconto=" + totalComDesconto + ", emEstoque=" + emEstoque + ", dataEntrega=" + dataEntrega
                + "]";
    }

    

}
