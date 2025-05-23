package com.ivanppf.desafio_klok.presentation.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanppf.desafio_klok.business.services.Item.ItemService;
import com.ivanppf.desafio_klok.model.entities.Item;
import com.ivanppf.desafio_klok.presentation.DTO.request.ItemRequestDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> buscarTodos() {
        List<Item> itens = itemService.buscarTodos();
        return ResponseEntity.ok(itens);
    }

    @PostMapping
    public ResponseEntity<Item> salvar(@RequestBody @Valid ItemRequestDTO obj) {
        Item item = new Item(obj.nome(), obj.preco(), obj.estoque());
        item = itemService.salvar(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
    
}
