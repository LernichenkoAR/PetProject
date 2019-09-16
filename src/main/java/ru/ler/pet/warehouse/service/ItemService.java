package ru.ler.pet.warehouse.service;

import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.entity.Item;

import java.util.List;

public interface ItemService {

    List<ItemDTO> findAll();

    ItemDTO findById(Long id) throws Throwable;

    List<ItemDTO> findByWarehouse(Long warehouse_id);

    List<ItemDTO> saveAll(List<Item> item);

}
