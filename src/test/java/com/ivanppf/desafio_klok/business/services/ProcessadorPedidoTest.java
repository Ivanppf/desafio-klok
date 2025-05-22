package com.ivanppf.desafio_klok.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.model.entities.enums.TipoCliente;

@SpringBootTest
public class ProcessadorPedidoTest {

    @MockitoBean
    CalculadorPreco calculadorPreco;
    @MockitoBean
    Notificador notificador;
    @MockitoBean
    ValidadorEstoque validadorEstoque;

    @Test
    void deveProcessarPedidoClienteComumComSucesso() {
        var pedido = new Pedido(
                new Cliente("Maria", "maria@email.com", TipoCliente.COMUM),
                List.of(new Item("Produto 1", 200.0, 1, 10), new Item("Produto 2", 150.0, 2, 10)));

        var processadorPedido = new ProcessadorPedidoImpl(calculadorPreco, validadorEstoque, notificador);
        when(calculadorPreco.calcularTotal(pedido)).thenReturn(500.0);
        when(calculadorPreco.aplicarDesconto(500.0, pedido.getCliente())).thenReturn(500.0);
        when(validadorEstoque.estaEmEstoque(pedido)).thenReturn(true);
        doNothing().when(notificador).enviarNotificacao(pedido.getCliente().getEmail(),
                "Seu pedido será entregue em breve.");

        processadorPedido.processar(pedido);

        assertEquals(500.0, pedido.getTotal());
        assertEquals(500.0, pedido.getTotalComDesconto());
        assertEquals(true, pedido.getEmEstoque());
        assertEquals(LocalDate.now().plusDays(3), pedido.getDataEntrega());


    }

    @Test
    void deveProcessarPedidoItemEsgotadoClienteComumComErro() {
        var pedido = new Pedido(
                new Cliente("Maria", "maria@email.com", TipoCliente.COMUM),
                List.of(new Item("Produto 1", 200.0, 1, 10), new Item("Produto 2", 150.0, 2, 1)));

        var processadorPedido = new ProcessadorPedidoImpl(calculadorPreco, validadorEstoque, notificador);
        when(calculadorPreco.calcularTotal(pedido)).thenReturn(500.0);
        when(calculadorPreco.aplicarDesconto(500.0, pedido.getCliente())).thenReturn(500.0);
        when(validadorEstoque.estaEmEstoque(pedido)).thenReturn(false);
        doNothing().when(notificador).enviarNotificacao(pedido.getCliente().getEmail(),
                "Seu pedido será entregue em breve.");

        processadorPedido.processar(pedido);

        assertEquals(500.0, pedido.getTotal());
        assertEquals(500.0, pedido.getTotalComDesconto());
        assertEquals(false, pedido.getEmEstoque());
        assertEquals(null, pedido.getDataEntrega());


    }
}