package ru.ler.pet.warehouse.service;

import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.entity.Item;

import java.util.List;

public interface ItemService {

    List<ItemDTO> getAll();

    ItemDTO getById(Long id) throws Throwable;

    List<ItemDTO> getByWarehouse(Long warehouse_id);

    void saveAll(List<Item> item);

}
