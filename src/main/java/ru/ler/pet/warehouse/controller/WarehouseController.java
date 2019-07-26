package ru.ler.pet.warehouse.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ler.pet.warehouse.model.Warehouse;
import ru.ler.pet.warehouse.model.WarehouseDTO;
import ru.ler.pet.warehouse.service.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by 16984608 on 24.07.2019.
 */
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1")

public class WarehouseController {
    private WarehouseRepository repository;


    @GetMapping("/warehouses")
    public List<WarehouseDTO> getAll() {
        List<WarehouseDTO> dtos = new ArrayList<>();
        repository.findAll().forEach(wh -> dtos.add(new WarehouseDTO(wh.getWarehouseID(), wh.getName())));
        return dtos;
    }

    @GetMapping("/warehouses/{warehouse_id}")
    public WarehouseDTO getWarehouseByID(@PathVariable("warehouse_id") Long id) {
        Optional<Warehouse> optional = repository.findById(id);
        if (optional.isPresent()){
            Warehouse warehouse = optional.get();
            return new WarehouseDTO(warehouse.getWarehouseID(), warehouse.getName());
        } else {
            throw new WarehouseNotFoundException();
        }

    }
}
