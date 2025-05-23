package com.ivanppf.desafio_klok.business.services.Pedido;

import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Pedido;

public interface CalculadorPreco {

    double calcularTotal(Pedido pedido);

    double aplicarDesconto(double total, Cliente cliente);

}