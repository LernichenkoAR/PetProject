package ru.ler.pet.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ler.pet.warehouse.exception.EntityNotFoundException;
import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.domen.ItemMapper;
import ru.ler.pet.warehouse.model.entity.Item;
import ru.ler.pet.warehouse.repository.ItemRepository;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor()
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    @Override
    public List<ItemDTO> findAll() {
        return repository.findAll().stream()
                .map(ItemMapper::from)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO findById(Long id) throws Throwable {
        return ItemMapper.from(repository.findById(id)
                .orElseThrow((Supplier<Throwable>) () -> new EntityNotFoundException("Can not find Item")));
    }

    @Override
    public List<ItemDTO> findByWarehouse(Long warehouse_id) {
        return repository.findByWarehouse(warehouse_id).stream().map(ItemMapper::from).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> saveAll(List<Item> items) {
        if (items.size() > 0) {
            return repository.saveAll(items).stream().map(ItemMapper::from).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
