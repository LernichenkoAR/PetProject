package ru.ler.pet.warehouse.service;

import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;

import java.util.List;

interface WarehouseService {
    List<WarehouseDTO> getAll();

    WarehouseDTO getByID(Long id) throws Throwable;

    List<ProductDTO> getAllProductsOfWarehouse(Long warehouseID) throws Throwable;

    List<ItemDTO> getAllItemsOfWarehouse(Long warehouseID) throws Throwable;

    void createNew(WarehouseDTO warehouse);

    void saveAllItems(List<ItemDTO> items, Long warehouse_id);
}
