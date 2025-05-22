package com.ivanppf.desafio_klok.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.model.entities.enums.TipoCliente;

@SpringBootTest
class CalculadorPrecoTest {

    @Test
    @DisplayName("Calcula o total do pedido corretamente cliente vip")
    void deveAplicarDescontoCLienteVip() {
        var cliente = new Cliente("João", "joao@mail.com", TipoCliente.VIP);
        List<Item> itens = List.of(new Item("Produto 1", 100.0, 2, 5), new Item("Produto 2", 50.0, 1, 5));
        var pedido = new Pedido(cliente, itens);

        double total = new CalculadorPreco().calcularTotal(pedido);
        double totalComDesconto = new CalculadorPreco().aplicarDesconto(total, cliente);

        assertEquals(250.0, total);
        assertEquals(225.0, totalComDesconto);
    }

    @Test
    @DisplayName("Calcula o total do pedido corretamente cliente normal")
    void deveAplicarDescontoCLienteNormal() {
        var cliente = new Cliente("João", "joao@mail.com", TipoCliente.COMUM);
        List<Item> itens = List.of(new Item("Produto 1", 100.0, 2, 5), new Item("Produto 2", 50.0, 1, 5));
        var pedido = new Pedido(cliente, itens);

        double total = new CalculadorPreco().calcularTotal(pedido);
        double totalComDesconto = new CalculadorPreco().aplicarDesconto(total, cliente);

        assertEquals(250.0, total);
        assertEquals(250.0, totalComDesconto);
    }

}
