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
import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.entity.Item;
import ru.ler.pet.warehouse.model.entity.Product;
import ru.ler.pet.warehouse.model.entity.Warehouse;

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
        resQuery.add(Item.newInstance(0L, Product.ofNameAndVolume("Barney Bear", 3),
                Warehouse.newInstance(0l, "Central")));
        resQuery.add(Item.newInstance(1L, Product.ofNameAndVolume("Honey", 1),
                Warehouse.newInstance(1L, "O'Rilly")));
    }

    @Test
    void getAll() {
        Mockito.when(repository.findAll()).thenReturn(resQuery);
        Assertions.assertNotNull(service.getAll());
        Assertions.assertEquals(service.getAll().size(), 2);
    }

    @Test
    void getById() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenAnswer((Answer<Optional<Item>>) invocationOnMock -> {
            Long arg = invocationOnMock.getArgument(0);
            int index = arg.intValue();
            return Optional.of(resQuery.get(index));
        });
        ItemDTO result = null;
        try {
            result = service.getById(1L);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, resQuery.get(1));
    }

    @Test
    void getByWarehouse() {
        Mockito.when(repository.findByWarehouse(Mockito.anyLong())).thenAnswer((Answer<List<Item>>) invocationOnMock -> {
            long id = invocationOnMock.getArgument(0);
            List<Item> result = new ArrayList<>();
            resQuery.forEach(r -> {
                if (r.getWarehouse().getId() == id) {
                    result.add(r);
                }
            });
            return result;
        });
        List<ItemDTO> result = service.getByWarehouse(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 1);

    }
}