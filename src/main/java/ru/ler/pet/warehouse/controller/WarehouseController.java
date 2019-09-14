package ru.ler.pet.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
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
        return service.getByID(warehouse_id);
    }

    @PostMapping("/warehouses")
    public void createNewWarehouse(@RequestBody WarehouseDTO wh) {
        service.createNew(wh);
    }

    @GetMapping("/warehouses/{warehouse_id}/products")
    public List<ProductDTO> getAllProductOfWarehouse(@PathVariable("warehouse_id") Long warehouse_id) {
        return service.getAllProductsOfWarehouse(warehouse_id);
    }

    @GetMapping("/warehouses/{warehouse_id}/items")
    public List<ItemDTO> getAllItems(@PathVariable("warehouse_id") Long warehouse_id) {
        return service.getAllItemsOfWarehouse(warehouse_id);
    }

    @PostMapping("/warehouses/{warehouse_id}/items")
    public void addNewItems(@RequestBody List<ItemDTO> items, @PathVariable("warehouse_id") Long warehouse_id) {
        service.saveAllItems(items, warehouse_id);
    }


}
