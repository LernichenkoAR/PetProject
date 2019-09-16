package ru.ler.pet.warehouse.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ler.pet.warehouse.exception.BadRequestException;
import ru.ler.pet.warehouse.exception.EntityNotFoundException;
import ru.ler.pet.warehouse.model.domen.*;
import ru.ler.pet.warehouse.model.entity.Item;
import ru.ler.pet.warehouse.model.entity.Product;
import ru.ler.pet.warehouse.model.entity.Warehouse;
import ru.ler.pet.warehouse.model.request.WarehouseCreateRequest;
import ru.ler.pet.warehouse.repository.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor()
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repository;
    private final ItemService itemService;
    private final ProductService productService;

    public List<WarehouseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(WarehouseMapper::from)
                .collect(Collectors.toList());
    }

    public WarehouseDTO findById(Long id) {
        if (id >= 0) {
            return WarehouseMapper.from(repository.findById(id)
                    .orElseThrow((Supplier<RuntimeException>) () -> new EntityNotFoundException("Can not find warehouse")));
        } else {
            throw new BadRequestException();
        }

    }

    @Override
    public List<ProductDTO> findAllProductsOfWarehouse(Long warehouseID) {
        return repository.findById(warehouseID)
                .orElseThrow((Supplier<RuntimeException>) () -> new EntityNotFoundException("Can not find warehouse"))
                .getUniqProducts()
                .stream()
                .map(ProductMapper::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> findAllItemsOfWarehouse(Long warehouseID) {
        return repository.findById(warehouseID)
                .orElseThrow((Supplier<RuntimeException>) () -> new EntityNotFoundException("Can not find warehouse"))
                .getItems().stream()
                .map(ItemMapper::from).collect(Collectors.toList());
    }

    @Override
    public WarehouseDTO save(WarehouseCreateRequest warehouse) {
        return WarehouseMapper.from(repository.save(WarehouseMapper.to(warehouse)));
    }

    @Override
    public List<ItemDTO> loadProduct(Long warehouse_id, Long product_id, Integer quantity) {
        Warehouse warehouse = WarehouseMapper.to(findById(warehouse_id));
        Product product = ProductMapper.to(productService.findById(product_id));
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            items.add(Item.newInstance(null, product, warehouse));
        }
        return itemService.saveAll(items);
    }
}
