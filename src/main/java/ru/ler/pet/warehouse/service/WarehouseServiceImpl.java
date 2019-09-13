package ru.ler.pet.warehouse.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ler.pet.warehouse.exception.BadRequestException;
import ru.ler.pet.warehouse.exception.EntityNotFoundException;
import ru.ler.pet.warehouse.model.domen.*;
import ru.ler.pet.warehouse.model.entity.Item;
import ru.ler.pet.warehouse.model.entity.Product;
import ru.ler.pet.warehouse.model.entity.Warehouse;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor()
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repository;
    private final ItemService itemService;
    private final ProductService productService;

    public List<WarehouseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(WarehouseMapper::from)
                .collect(Collectors.toList());
    }

    public WarehouseDTO getByID(Long id) {
        if (id >= 0) {
            return WarehouseMapper.from(repository.findById(id)
                    .orElseThrow((Supplier<RuntimeException>) () -> new EntityNotFoundException("Can not find warehouse")));
        } else {
            throw new BadRequestException();
        }

    }

    @Override
    public List<ProductDTO> getAllProductsOfWarehouse(Long warehouseID) {
        return repository.findById(warehouseID)
                .orElseThrow((Supplier<RuntimeException>) () -> new EntityNotFoundException("Can not find warehouse"))
                .getUniqProducts()
                .stream()
                .map(ProductMapper::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllItemsOfWarehouse(Long warehouseID) {
        return repository.findById(warehouseID)
                .orElseThrow((Supplier<RuntimeException>) () -> new EntityNotFoundException("Can not find warehouse"))
                .getItems().stream()
                .map(ItemMapper::from).collect(Collectors.toList());
    }

    @Override
    public void createNew(WarehouseDTO dto) {
        repository.save(WarehouseMapper.to(dto));
    }

    @Override
    public void saveAllItems(List<ItemDTO> items, Long warehouse_id) {

        List<Item> its = items.stream()
                .map(i -> {
                    Product p = ProductMapper.to(productService.getById(i.getProduct()));
                    Warehouse w = WarehouseMapper.to(getByID(warehouse_id));
                    return Item.newInstance(null, p, w);
                })
                .collect(Collectors.toList());

        itemService.saveAll(its);
    }
}
