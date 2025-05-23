package com.ivanppf.desafio_klok.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ivanppf.desafio_klok.business.services.Cliente.ClienteService;
import com.ivanppf.desafio_klok.business.services.Item.ItemService;
import com.ivanppf.desafio_klok.business.services.Pedido.PedidoService;
import com.ivanppf.desafio_klok.business.services.Pedido.ProcessadorPedido;
import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.ItemPedido;
import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.model.entities.enums.TipoCliente;
import com.ivanppf.desafio_klok.model.repositories.PedidoRepository;
import com.ivanppf.desafio_klok.presentation.DTO.request.ItemPedidoRequestDTO;
import com.ivanppf.desafio_klok.presentation.DTO.request.PedidoRequestDTO;

@SpringBootTest
public class PedidoServiceTest {

    @MockitoBean
    ProcessadorPedido processadorPedido;
    @MockitoBean
    PedidoRepository pedidoRepository;
    @MockitoBean
    ClienteService clienteService;
    @MockitoBean
    ItemService itemService;
    PedidoService pedidoService;

    @BeforeEach
    void setup() {
        pedidoService = new PedidoService(pedidoRepository, processadorPedido, clienteService, itemService);
    }

    @Test
    void deveProcessarVariosPedidosComSucesso() {
        ItemPedido itemPedido1 = new ItemPedido();
        itemPedido1.setItem(new Item("Produto 3", 200.0, 10));
        itemPedido1.setQuantidade(1);
        ItemPedido itemPedido2 = new ItemPedido();
        itemPedido2.setItem(new Item("Produto 4", 150.0, 3));
        itemPedido2.setQuantidade(2);
        var pedidos = List.of(
                new Pedido(
                        new Cliente("Maria", "maria@email.com", TipoCliente.COMUM)),
                new Pedido(
                        new Cliente("Carlos", "carlos@email.com", TipoCliente.VIP)));

        pedidos.get(0).setItens(List.of(itemPedido1));
        pedidos.get(1).setItens(List.of(itemPedido2));

        Pedido pedido1 = pedidos.get(0);
        Pedido pedido2 = pedidos.get(1);

        Pedido pedidoProcessado1 = new Pedido(
                pedido1.getCliente());
        pedidoProcessado1.setItens(pedido1.getItens());

        Pedido pedidoProcessado2 = new Pedido(
                pedido2.getCliente());
        pedidoProcessado2.setItens(pedido2.getItens());

        when(processadorPedido.processar(pedido1)).thenReturn(pedidoProcessado1);
        when(processadorPedido.processar(pedido2)).thenReturn(pedidoProcessado2);

        List<Pedido> pedidosProcessados = pedidoService.processarPedidos(pedidos);

        assertEquals(pedidos, pedidosProcessados);

    }

    @Test
    void deveSalvarPedidoComSucesso() {
        var cliente = new Cliente("João", "joao@email.com", TipoCliente.COMUM);
        var item = new Item("Produto 1", 100.0, 5);

        var itemPedidoRequestDTO = new ItemPedidoRequestDTO(item.getId(), 2);
        var pedidoRequestDTO = new PedidoRequestDTO(cliente.getId(), List.of(itemPedidoRequestDTO));

        when(clienteService.buscarPorId(cliente.getId())).thenReturn(cliente);
        when(itemService.buscarTodosPorId(List.of(UUID.randomUUID()))).thenReturn(List.of(item));
        when(processadorPedido.processar(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido pedido = invocation.getArgument(0);
            pedido.setDataEntrega(LocalDate.now().plusDays(3));
            return pedido;
        });
        Pedido pedido = new Pedido(cliente);
        pedido.setItens(List.of(new ItemPedido(pedido, item, 2)));
        pedido.setDataEntrega(LocalDate.now().plusDays(3));

        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido pedidoSalvo = pedidoService.salvar(pedidoRequestDTO);

        assertEquals(cliente, pedidoSalvo.getCliente());
        assertEquals(1, pedidoSalvo.getItens().size());
        assertEquals(item, pedidoSalvo.getItens().get(0).getItem());
        assertEquals(2, pedidoSalvo.getItens().get(0).getQuantidade());
        assertEquals(LocalDate.now().plusDays(3), pedidoSalvo.getDataEntrega());
    }

}
