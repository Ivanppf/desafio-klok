package com.ivanppf.desafio_klok.business.services.Pedido;

import org.springframework.stereotype.Component;

import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.ItemPedido;
import com.ivanppf.desafio_klok.model.entities.Pedido;

@Component
public class CalculadorPrecoImpl implements CalculadorPreco {

    @Override
    public double calcularTotal(Pedido pedido) {
        double total = 0;

        for (ItemPedido item : pedido.getItens()) {
            total += item.getItem().getPreco() * item.getQuantidade();
        }

        return total;
    }

    @Override
    public double aplicarDesconto(double total, Cliente cliente) {
        return total * (100 - cliente.getTipo().getValor()) / 100;
    }

}
