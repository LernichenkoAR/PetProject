package ru.ler.pet.warehouse.controller;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ru.ler.pet.warehouse.model.Warehouse;
import ru.ler.pet.warehouse.model.WarehouseDTO;
import ru.ler.pet.warehouse.service.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by 16984608 on 24.07.2019.
 */
@ExtendWith(MockitoExtension.class)
public class WarehouseControllerTest {


    @Mock
    private WarehouseRepository warehouseRepository;
    @InjectMocks
    private WarehouseController warehouseController;


    private static List<Warehouse> l = new ArrayList<>();

    @BeforeAll
    public static void init() {
        l.add(new Warehouse("O'Niel WH"));
        l.add(new Warehouse("Central WH"));
        l.add(new Warehouse("Fruit WH"));
        l.add(new Warehouse("Drone WH"));
        l.add(new Warehouse("Goods WH"));

    }

    @Test
    public void getAllTest() {
        Mockito.when(warehouseRepository.findAll()).thenReturn(l);
        List<WarehouseDTO> result = warehouseController.getAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 5);
    }
    @Test
    public void getByIdTest(){
        Mockito.when(warehouseRepository.findById(Mockito.anyLong())).thenAnswer((Answer<Optional<Warehouse>>)
                invocation -> {
            Long arg = invocation.getArgument(0);
            Integer index = arg.intValue();
            return  Optional.of(l.get(index));
        });

        WarehouseDTO result = warehouseController.getWarehouseByID(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(), "Central WH");
    }

}
