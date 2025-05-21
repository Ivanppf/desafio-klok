package com.ivanppf.desafio_klok.business.services;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.ivanppf.desafio_klok.model.entities.Pedido;

@Component
public class ProcessadorPedidoImpl implements ProcessadorPedido {

    private final CalculadorPreco calculadorPreco;
    private final ValidadorEstoque validadorEstoque;
    private final Notificador notificador;

    public ProcessadorPedidoImpl(CalculadorPreco calculadorPreco, ValidadorEstoque estoqueValidator, Notificador notificador) {
        this.calculadorPreco = calculadorPreco;
        this.validadorEstoque = estoqueValidator;
        this.notificador = notificador;
    }

    @Override
    public Pedido processar(Pedido pedido) {
        double total = calculadorPreco.calcularTotal(pedido);
        double totalComDesconto = calculadorPreco.aplicarDesconto(total, pedido.getCliente());

        pedido.setTotal(total);
        pedido.setTotalComDesconto(totalComDesconto);

        boolean emEstoque = validadorEstoque.estaEmEstoque(pedido);
        pedido.setEmEstoque(emEstoque);
        pedido.setDataEntrega(emEstoque ? LocalDate.now().plusDays(3) : null);

        String mensagem = emEstoque
                ? "Seu pedido será entregue em breve."
                : "Um ou mais itens do seu pedido estão fora de estoque.";

        notificador.enviarNotificacao(pedido.getCliente().getEmail(), mensagem);

        return pedido;

    }
}
