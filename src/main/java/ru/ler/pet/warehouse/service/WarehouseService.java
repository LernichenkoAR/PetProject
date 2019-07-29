package ru.ler.pet.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ler.pet.warehouse.exception.WarehouseNotFoundException;
import ru.ler.pet.warehouse.model.entity.Warehouse;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor()
public class WarehouseService {

    private final WarehouseRepository repository;

    public List<WarehouseDTO> getAll() {
        List<WarehouseDTO> dtoList = new ArrayList<>();
        repository.findAll().forEach(wh -> dtoList.add(new WarehouseDTO(wh.getId(), wh.getName())));
        return dtoList;
    }
    public WarehouseDTO getWarehouseByID(@PathVariable("warehouse_id") Long id) {
        Optional<Warehouse> optional = repository.findById(id);
        if (optional.isPresent()){
            Warehouse warehouse = optional.get();
            return new WarehouseDTO(warehouse.getId(), warehouse.getName());
        } else {
            throw new WarehouseNotFoundException();
        }
    }
}
