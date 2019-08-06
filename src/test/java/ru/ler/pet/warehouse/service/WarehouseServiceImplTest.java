package ru.ler.pet.warehouse.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.model.entity.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class WarehouseServiceImplTest {

    @Mock
    private WarehouseRepository warehouseRepository;
    @InjectMocks
    private WarehouseServiceImpl warehouseServiceImpl;


    private static List<Warehouse> l = new ArrayList<>();

    @BeforeAll
    public static void init() {
        l.add(Warehouse.ofName("O'Niel WH"));
        l.add(Warehouse.ofName("Central WH"));
        l.add(Warehouse.ofName("Fruit WH"));
        l.add(Warehouse.ofName("Drone WH"));
        l.add(Warehouse.ofName("Goods WH"));

    }

    @Test
    public void getAllTest() {
        Mockito.when(warehouseRepository.findAll()).thenReturn(l);
        List<WarehouseDTO> result = warehouseServiceImpl.getAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 5);
    }

    @Test
    public void getByIdTest() {
        Mockito.when(warehouseRepository.findById(Mockito.anyLong())).thenAnswer((Answer<Optional<Warehouse>>)
                invocation -> {
                    Long arg = invocation.getArgument(0);
                    Integer index = arg.intValue();
                    return Optional.of(l.get(index));
                });

        WarehouseDTO result = warehouseServiceImpl.getWarehouseByID(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(), "Central WH");
    }
}
