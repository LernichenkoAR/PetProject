package ru.ler.pet.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ler.pet.warehouse.exception.BadRequestException;
import ru.ler.pet.warehouse.exception.WarehouseNotFoundException;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.model.domen.WarehouseDTOFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor()
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repository;

    public List<WarehouseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(WarehouseDTOFactory::from)
                .collect(Collectors.toList());
    }

    public WarehouseDTO getWarehouseByID(Long id) {
        if (id > 0) {
            return WarehouseDTOFactory.from(repository.findById(id).orElseThrow(WarehouseNotFoundException::new));
        } else {
            throw new BadRequestException();
        }

    }
}
