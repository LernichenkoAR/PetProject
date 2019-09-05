package ru.ler.pet.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ler.pet.warehouse.exception.BadRequestException;
import ru.ler.pet.warehouse.exception.EntityNotFoundException;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.ProductDTOFactory;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.model.domen.WarehouseDTOFactory;

import java.util.List;
import java.util.function.Supplier;
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

    public WarehouseDTO getWarehouseByID(Long id) throws Throwable {
        if (id > 0) {
            return WarehouseDTOFactory.from(repository.findById(id)
                    .orElseThrow((Supplier<Throwable>) () -> new EntityNotFoundException("Can not find warehouse")));
        } else {
            throw new BadRequestException();
        }

    }

    @Override
    public List<ProductDTO> getAllProductsOfWarehouse(Long warehouseID) throws Throwable {
        return repository.findById(warehouseID)
                .orElseThrow((Supplier<Throwable>) () -> new EntityNotFoundException("Can not find warehouse"))
                .getProducts()
                .stream()
                .map(ProductDTOFactory::from)
                .collect(Collectors.toList());
    }
}
