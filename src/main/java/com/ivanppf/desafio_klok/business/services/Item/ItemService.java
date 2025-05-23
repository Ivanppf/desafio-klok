package com.ivanppf.desafio_klok.business.services.Item;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.model.repositories.ItemRepository;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item buscarPorId(UUID id) {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));
    }

    public List<Item> buscarTodos() {
        return itemRepository.findAll();
    }

    public List<Item> buscarTodosPorId(List<UUID> ids) {
        return itemRepository.findAllById(ids);
    }

    public Item salvar(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> atualizarLista(List<UUID> ids, List<Item> novoItem) {
        List<Item> itensExistentes = itemRepository.findAllById(ids);
        if (itensExistentes.size() != ids.size()) {
            throw new IllegalArgumentException("Um ou mais itens não foram encontrados");
        }
        for (int i = 0; i < ids.size(); i++) {
            novoItem.get(i).setId(ids.get(i));
        }
        return itemRepository.saveAll(novoItem);
    }

}
