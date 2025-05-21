package com.ivanppf.desafio_klok.business.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.Pedido;

@Service
public class PedidoService {

    public void processarPedidos(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            double total = 0;

            for (Item item : pedido.getItems()) {
                total += item.getPreco() * item.getQuantidade();
            }

            pedido.setTotal(total);

            if (pedido.getCliente().isVip()) {
                total *= 0.9;
            }

            pedido.setTotalComDesconto(total);

            boolean emEstoque = true;
            for (Item item : pedido.getItems()) {
                if (item.getQuantidade() > item.getEstoque()) {
                    emEstoque = false;
                    break;
                }
            }
            pedido.setEmEstoque(emEstoque);

            if (emEstoque) {
                pedido.setDataEntrega(LocalDate.now().plusDays(3));
            } else {
                pedido.setDataEntrega(null);
            }

            if (emEstoque) {
                enviarNotificacao(pedido.getCliente().getEmail(), "Seu pedido será entregue em breve.");
            } else {
                enviarNotificacao(pedido.getCliente().getEmail(),
                        "Um ou mais itens do seu pedido estão fora de estoque.");
            }
        }
    }

    private void enviarNotificacao(String email, String mensagem) {
        System.out.println("Enviando e-mail para " + email + ": " + mensagem);
    }
}
