package ru.ler.pet.warehouse.service;

import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.model.request.WarehouseCreateRequest;

import java.util.List;

interface WarehouseService {
    List<WarehouseDTO> findAll();

    WarehouseDTO findById(Long id) throws Throwable;

    List<ProductDTO> findAllProductsOfWarehouse(Long warehouseID) throws Throwable;

    List<ItemDTO> findAllItemsOfWarehouse(Long warehouseID) throws Throwable;

    WarehouseDTO save(WarehouseCreateRequest dto);

    List<ItemDTO> loadProduct(Long warehouse_id, Long product_id, Integer quantity);
}
