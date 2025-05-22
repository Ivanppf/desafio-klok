package com.ivanppf.desafio_klok.business.services;

import org.springframework.stereotype.Component;

import com.ivanppf.desafio_klok.model.entities.Pedido;

@Component
public class ValidadorEstoque {

    public boolean estaEmEstoque(Pedido pedido) {
        for (var item : pedido.getItens()) {
            if (item.getQuantidade() > item.getEstoque()) {
                return false;
            }
        }
        return true;
    }

}
