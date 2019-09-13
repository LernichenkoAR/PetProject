package ru.ler.pet.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ler.pet.warehouse.exception.EntityNotFoundException;
import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.domen.ItemMapper;
import ru.ler.pet.warehouse.model.entity.Item;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor()
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    @Override
    public List<ItemDTO> getAll() {
        return repository.findAll().stream()
                .map(ItemMapper::from)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getById(Long id) throws Throwable {
        return ItemMapper.from(repository.findById(id)
                .orElseThrow((Supplier<Throwable>) () -> new EntityNotFoundException("Can not find Item")));
    }

    @Override
    public List<ItemDTO> getByWarehouse(Long warehouse_id) {
        return repository.findByWarehouse(warehouse_id).stream().map(ItemMapper::from).collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Item> items) {
        if (items.size() > 0) {
            repository.saveAll(items);
        }
    }
}
