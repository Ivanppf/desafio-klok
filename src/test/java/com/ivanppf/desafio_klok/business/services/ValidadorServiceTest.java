package com.ivanppf.desafio_klok.business.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.model.entities.enums.TipoCliente;

@SpringBootTest
public class ValidadorServiceTest {
    
    @Test
    public void testValidadorEmEstoque() {
        var cliente = new Cliente("João", "joao@mail.com", TipoCliente.VIP);
        List<Item> itens = List.of(new Item("Produto 1", 100.0, 2, 5), new Item("Produto 2", 50.0, 1, 5));
        var pedido = new Pedido(cliente, itens);
    
        ValidadorEstoque validadorEstoque = new ValidadorEstoque();
        boolean resultado = validadorEstoque.estaEmEstoque(pedido);
    
        assertTrue(resultado);
    }

    @Test
    public void testValidadorSemEstoque() {
        var cliente = new Cliente("João", "joao@mail.com", TipoCliente.VIP);
        List<Item> itens = List.of(new Item("Produto 1", 100.0, 2, 1), new Item("Produto 2", 50.0, 1, 5));
        var pedido = new Pedido(cliente, itens);
    
        ValidadorEstoque validadorEstoque = new ValidadorEstoque();
        boolean resultado = validadorEstoque.estaEmEstoque(pedido);
    
        assertFalse(resultado);
    }
}
