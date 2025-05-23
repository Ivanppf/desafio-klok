package com.ivanppf.desafio_klok.business.services.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ivanppf.desafio_klok.business.services.Cliente.ClienteService;
import com.ivanppf.desafio_klok.business.services.Item.ItemService;
import com.ivanppf.desafio_klok.model.entities.Cliente;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.entities.ItemPedido;
import com.ivanppf.desafio_klok.model.entities.Pedido;
import com.ivanppf.desafio_klok.model.repositories.PedidoRepository;
import com.ivanppf.desafio_klok.presentation.DTO.request.PedidoRequestDTO;

@Service
public class PedidoService {

    private final ProcessadorPedido processadorPedido;
    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ItemService itemService;

    public PedidoService(PedidoRepository pedidoRepository, ProcessadorPedido processadorPedido,
            ClienteService clienteService, ItemService itemService) {
        this.pedidoRepository = pedidoRepository;
        this.processadorPedido = processadorPedido;
        this.clienteService = clienteService;
        this.itemService = itemService;
    }

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido salvar(PedidoRequestDTO obj) {
        Cliente c = clienteService.buscarPorId(obj.idCliente());
        List<UUID> itemIds = obj.itens().stream().map(i -> {
            return i.idItem();
        }).toList(); 
        List<Item> itens = itemService.buscarTodosPorId(itemIds);
        List<ItemPedido> itemPedidos = new ArrayList<>();
        Pedido pedido = new Pedido(c);
        for (int i = 0; i < itens.size(); i++) {
            itemPedidos.add(new ItemPedido(pedido, itens.get(i), obj.itens().get(i).quantidade()));
        }

        pedido.setItens(itemPedidos);
        pedido = processarPedidos(List.of(pedido)).get(0);
        if (pedido.getDataEntrega() == null) {
            throw new IllegalArgumentException("Pedido não pode ser salvo, pois não há estoque.");
        }
        Pedido p = pedidoRepository.save(pedido); 
        for (int i = 0; i < itens.size(); i++) {
            itens.get(i).setEstoque(itens.get(i).getEstoque() - itemPedidos.get(i).getQuantidade());
        }

        itemService.atualizarLista(itemIds, itens);
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
