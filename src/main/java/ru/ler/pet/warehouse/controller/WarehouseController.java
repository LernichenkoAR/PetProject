package ru.ler.pet.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.model.request.WarehouseCreateRequest;
import ru.ler.pet.warehouse.service.WarehouseServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
class WarehouseController {

    private final WarehouseServiceImpl service;

    @GetMapping("/warehouses")
    public List<WarehouseDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/warehouses/{warehouse_id}")
    public WarehouseDTO getWarehouseByID(@PathVariable("warehouse_id") Long warehouse_id) {
        return service.findById(warehouse_id);
    }

    @PostMapping("/warehouses")
    public void createNewWarehouse(@RequestBody WarehouseCreateRequest wh) {
        service.save(wh);
    }

    @GetMapping("/warehouses/{warehouse_id}/products")
    public List<ProductDTO> getAllProductOfWarehouse(@PathVariable("warehouse_id") Long warehouse_id) {
        return service.findAllProductsOfWarehouse(warehouse_id);
    }

    @GetMapping("/warehouses/{warehouse_id}/items")
    public List<ItemDTO> getAllItems(@PathVariable("warehouse_id") Long warehouse_id) {
        return service.findAllItemsOfWarehouse(warehouse_id);
    }


    @PostMapping("/warehouse/load/{warehouse_id}/{product_id}/{quantity}")
    public void loadProduct(@PathVariable("warehouse_id") Long warehouse_id,
                            @PathVariable("product_id") Long product_id,
                            @PathVariable("quantity") Integer quantity) {
        service.loadProduct(warehouse_id, product_id, quantity);
    }


}
