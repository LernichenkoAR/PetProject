package ru.ler.pet.warehouse.service;

import ru.ler.pet.warehouse.model.domen.WarehouseDTO;

import java.util.List;

public interface WarehouseService {
    List<WarehouseDTO> getAll();

    WarehouseDTO getWarehouseByID(Long id);
}
