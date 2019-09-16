package ru.ler.pet.warehouse.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ru.ler.pet.warehouse.ItemFactory;
import ru.ler.pet.warehouse.ProductFactory;
import ru.ler.pet.warehouse.WarehouseFactory;
import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.domen.ItemMapper;
import ru.ler.pet.warehouse.model.entity.Item;
import ru.ler.pet.warehouse.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {


    private static List<Item> resQuery;
    @Mock
    private ItemRepository repository;
    @InjectMocks
    private ItemServiceImpl service;

    @BeforeAll
    public static void init() {
        resQuery = new ArrayList<>();
        resQuery.add(ItemFactory.newItem(ProductFactory.newProduct("Barney Bear", 3),
                WarehouseFactory.newWarehouse( "Central")));
        resQuery.add(ItemFactory.newItem( ProductFactory.newProduct("Honey", 1),
                WarehouseFactory.newWarehouse( "O'Rilly")));
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(resQuery);
        Assertions.assertNotNull(service.findAll());
        Assertions.assertEquals(service.findAll().size(), 2);
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenAnswer((Answer<Optional<Item>>) invocationOnMock -> {
            Long arg = invocationOnMock.getArgument(0);
            int index = arg.intValue();
            return Optional.of(resQuery.get(index));
        });
        ItemDTO result = null;
        try {
            result = service.findById(1L);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, ItemMapper.from(resQuery.get(1)));
    }

    @Test
    void findByWarehouse() {
        Mockito.when(repository.findByWarehouse(Mockito.anyLong())).thenAnswer((Answer<List<Item>>) invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            return resQuery.get(id.intValue()).getWarehouse().getItems();
        });
        List<ItemDTO> result = service.findByWarehouse(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 1);
    }

    @Test
    void saveAllItems(){
        Mockito.when(repository.saveAll(Mockito.anyIterable())).thenAnswer((Answer<List<Item>>) invocation -> {
            Iterable iterable = invocation.getArgument(0);
            List<Item> result = new ArrayList<>();
            iterable.forEach(i -> result.add((Item) i));
            return result;
        });
        List<ItemDTO> result = service.saveAll(resQuery);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 2);
    }
}