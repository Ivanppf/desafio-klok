package com.ivanppf.desafio_klok.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.ivanppf.desafio_klok.model.entities.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    
}
