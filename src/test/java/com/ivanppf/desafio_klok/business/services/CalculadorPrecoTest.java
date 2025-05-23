package com.ivanppf.desafio_klok.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ivanppf.desafio_klok.business.services.Pedido.CalculadorPrecoImpl;
import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.ItemPedido;
import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.model.entities.enums.TipoCliente;

@SpringBootTest
class CalculadorPrecoTest {

    @Test
    @DisplayName("Calcula o total do pedido corretamente cliente vip")
    void deveAplicarDescontoCLienteVip() {
        var cliente = new Cliente("João", "joao@mail.com", TipoCliente.VIP);
        List<Item> itens = List.of(new Item("Produto 1", 100.0, 100), new Item("Produto 2", 50.0, 100));
        var pedido = new Pedido(cliente);

        ItemPedido itemPedido1 = new ItemPedido();
        itemPedido1.setItem(itens.get(0));
        itemPedido1.setQuantidade(2);

        ItemPedido itemPedido2 = new ItemPedido();
        itemPedido2.setItem(itens.get(1));
        itemPedido2.setQuantidade(1);

        pedido.setItens(List.of(itemPedido1, itemPedido2));

        double total = new CalculadorPrecoImpl().calcularTotal(pedido);
        double totalComDesconto = new CalculadorPrecoImpl().aplicarDesconto(total, cliente);

        assertEquals(250.0, total);
        assertEquals(225.0, totalComDesconto);
    }

    @Test
    @DisplayName("Calcula o total do pedido corretamente cliente normal")
    void deveAplicarDescontoCLienteNormal() {
        var cliente = new Cliente("João", "joao@mail.com", TipoCliente.COMUM);
        List<Item> itens = List.of(new Item("Produto 1", 100.0, 5), new Item("Produto 2", 50.0, 5));
        var pedido = new Pedido(cliente);

        List<ItemPedido> itemPedidos = List.of(
            new ItemPedido(pedido, itens.get(0), 2),
            new ItemPedido(pedido, itens.get(1), 1)
        );
        pedido.setItens(itemPedidos);

        double total = new CalculadorPrecoImpl().calcularTotal(pedido);
        double totalComDesconto = new CalculadorPrecoImpl().aplicarDesconto(total, cliente);

        assertEquals(250.0, total);
        assertEquals(250.0, totalComDesconto);
    }

}
