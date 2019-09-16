package ru.ler.pet.warehouse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ler.pet.warehouse.model.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAll();

    Optional<Item> findById(Long id);

    List<Item> findByWarehouse(Long warehouse_id);

    List<Item> saveAll(List<Item> items);
}
