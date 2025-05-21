package com.ivanppf.desafio_klok.business.services;

import org.springframework.stereotype.Component;

import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.Pedido;

@Component
public class CalculadorPreco {

    public double calcularTotal(Pedido pedido) {
        double total = 0;

        for (Item item : pedido.getItems()) {
            total += item.getPreco() * item.getQuantidade();
        }

        return total;
    }

    public double aplicarDesconto(double total, Cliente cliente) {
            return total * (100 - cliente.getTipo().getValor()) / 100;
    }

}
