package ru.ler.pet.warehouse.service;

import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;

import java.util.List;

interface WarehouseService {
    List<WarehouseDTO> getAll();

    WarehouseDTO getWarehouseByID(Long id) throws Throwable;
    List<ProductDTO> getAllProductsOfWarehouse(Long warehouseID) throws Throwable;
}
