package com.ivanppf.desafio_klok.business.services;

import com.ivanppf.desafio_klok.model.entities.Pedido;

public interface ValidadorEstoque {

    boolean estaEmEstoque(Pedido pedido);

}