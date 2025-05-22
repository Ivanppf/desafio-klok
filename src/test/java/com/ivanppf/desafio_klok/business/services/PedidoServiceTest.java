package com.ivanppf.desafio_klok.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.model.entities.enums.TipoCliente;
import com.ivanppf.desafio_klok.model.repositories.PedidoRepository;

@SpringBootTest
public class PedidoServiceTest {

    @MockitoBean
    ProcessadorPedido processadorPedido;
    @MockitoBean
    PedidoRepository pedidoRepository;
    PedidoService pedidoService;

    @BeforeEach
    void setup() {
        pedidoService = new PedidoService(processadorPedido, pedidoRepository);
    }

    @Test
    void deveProcessarVariosPedidosComSucesso() {
        var pedidos = List.of(
                new Pedido(
                        new Cliente("Maria", "maria@email.com", TipoCliente.COMUM),
                        List.of(new Item("Produto 3", 200.0, 1, 10))),
                new Pedido(
                        new Cliente("Carlos", "carlos@email.com", TipoCliente.VIP),
                        List.of(new Item("Produto 4", 150.0, 3, 2))));

        Pedido pedido1 = pedidos.get(0);
        Pedido pedido2 = pedidos.get(1);

        Pedido pedidoProcessado1 = new Pedido(
                pedido1.getCliente(),
                pedido1.getItens());
        Pedido pedidoProcessado2 = new Pedido(
                pedido2.getCliente(),
                pedido2.getItens());

        when(processadorPedido.processar(pedido1)).thenReturn(pedidoProcessado1);
        when(processadorPedido.processar(pedido2)).thenReturn(pedidoProcessado2);

        List<Pedido> pedidosProcessados = pedidoService.processarPedidos(pedidos);

        assertEquals(pedidos, pedidosProcessados);

    }

    @Test
    void deveSalvarPedidoComSucesso() {
        Cliente cliente = new Cliente("João", "joao@email.com", TipoCliente.COMUM);
        Item item = new Item("Produto 1", 100.0, 2, 5);
        Pedido pedido = new Pedido(cliente, List.of(item));

        Pedido pedidoProcessado = new Pedido(cliente, List.of(item));
        Pedido pedidoSalvo = new Pedido(cliente, List.of(item));

        when(processadorPedido.processar(pedido)).thenReturn(pedidoProcessado);
        when(pedidoRepository.save(pedido)).thenReturn(pedidoSalvo);

        Pedido resultado = pedidoService.salvar(pedido);

        assertEquals(pedidoSalvo, resultado);
    }

}
