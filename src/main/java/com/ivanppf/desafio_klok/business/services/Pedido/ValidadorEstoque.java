package com.ivanppf.desafio_klok.business.services.Pedido;

import com.ivanppf.desafio_klok.model.entities.Pedido;

public interface ValidadorEstoque {

    boolean estaEmEstoque(Pedido pedido);

}