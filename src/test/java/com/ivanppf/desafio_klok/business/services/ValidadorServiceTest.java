package com.ivanppf.desafio_klok.business.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ivanppf.desafio_klok.business.services.Pedido.ValidadorEstoque;
import com.ivanppf.desafio_klok.business.services.Pedido.ValidadorEstoqueImpl;
import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.ItemPedido;
import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.model.entities.enums.TipoCliente;

@SpringBootTest
public class ValidadorServiceTest {

    @Test
    public void testValidadorEmEstoque() {
        var cliente = new Cliente("João", "joao@mail.com", TipoCliente.VIP);
        List<Item> itens = List.of(new Item("Produto 1", 100.0, 5), new Item("Produto 2", 50.0, 5));
        var pedido = new Pedido(cliente);

        List<ItemPedido> itemPedidos = List.of(
                new ItemPedido(pedido, itens.get(0), 2),
                new ItemPedido(pedido, itens.get(1), 1));
        pedido.setItens(itemPedidos);

        ValidadorEstoque validadorEstoque = new ValidadorEstoqueImpl();
        boolean resultado = validadorEstoque.estaEmEstoque(pedido);

        assertTrue(resultado);
    }

    @Test
    public void testValidadorSemEstoque() {
        var cliente = new Cliente("João", "joao@mail.com", TipoCliente.VIP);
        List<Item> itens = List.of(new Item("Produto 1", 100.0, 1), new Item("Produto 2", 50.0, 5));

        var pedido = new Pedido(cliente);

        List<ItemPedido> itemPedidos = List.of(
                new ItemPedido(pedido, itens.get(0), 2),
                new ItemPedido(pedido, itens.get(1), 1));
        pedido.setItens(itemPedidos);

        ValidadorEstoque validadorEstoque = new ValidadorEstoqueImpl();
        boolean resultado = validadorEstoque.estaEmEstoque(pedido);

        assertFalse(resultado);
    }
}
