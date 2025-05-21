package com.ivanppf.desafio_klok.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.model.repositories.PedidoRepository;

@Service
public class PedidoService {

    private final ProcessadorPedido processadorPedido;
    private final PedidoRepository pedidoRepository;

    public PedidoService(ProcessadorPedido processadorPedido, PedidoRepository pedidoRepository) {
        this.processadorPedido = processadorPedido;
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido salvar(Pedido pedido) {
        processarPedidos(List.of(pedido));
        Pedido p = pedidoRepository.save(pedido);
        return p;
    }

    public List<Pedido> processarPedidos(List<Pedido> pedidos) {
        List<Pedido> pedidosSalvos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            Pedido p = processadorPedido.processar(pedido);
            pedidosSalvos.add(p);
        }
        return pedidosSalvos;
    }
}

