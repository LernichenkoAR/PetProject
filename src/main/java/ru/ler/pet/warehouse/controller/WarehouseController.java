package ru.ler.pet.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.service.WarehouseServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
class WarehouseController {

    @SuppressWarnings("unused")
    private final WarehouseServiceImpl service;

    @GetMapping("/warehouses")
    public List<WarehouseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/warehouses/{warehouse_id}")
    public WarehouseDTO getWarehouseByID(@PathVariable("warehouse_id") Long warehouse_id) {
        return service.getWarehouseByID(warehouse_id);
    }
}
