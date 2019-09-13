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
import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.model.entity.Item;
import ru.ler.pet.warehouse.model.entity.Product;
import ru.ler.pet.warehouse.model.entity.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceImplTest {

    private static final List<Warehouse> l = new ArrayList<>();
    private static final List<Item> i = new ArrayList<>();
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private ItemServiceImpl itemService;
    @Mock
    private ProductServiceImpl productService;
    @InjectMocks
    private WarehouseServiceImpl warehouseServiceImpl;


    @BeforeAll
    static void init() {
        l.add(Warehouse.newInstance(0L, "O'Niel WH"));
        l.add(Warehouse.newInstance(1L, "Central WH"));
        l.add(Warehouse.newInstance(2L, "Fruit WH"));
        l.add(Warehouse.newInstance(3L, "Drone WH"));
        l.add(Warehouse.newInstance(4L, "Goods WH"));

        i.add(Item.newInstance(0L, Product.ofNameAndVolume("Barney Bear", 3), l.get(0)));
        i.add(Item.newInstance(1L, Product.ofNameAndVolume("Barney Bear", 3), l.get(0)));
        i.add(Item.newInstance(2L, Product.ofNameAndVolume("Barney Bear", 3), l.get(0)));
        i.add(Item.newInstance(3L, Product.ofNameAndVolume("Barney Bear", 3), l.get(1)));
        i.add(Item.newInstance(4L, Product.ofNameAndVolume("Barney Bear", 3), l.get(1)));
        i.add(Item.newInstance(5L, Product.ofNameAndVolume("Barney Bear", 3), l.get(1)));
        i.add(Item.newInstance(6L, Product.ofNameAndVolume("Honey", 1), l.get(0)));
        i.add(Item.newInstance(7L, Product.ofNameAndVolume("Honey", 1), l.get(2)));
        i.add(Item.newInstance(8L, Product.ofNameAndVolume("Honey", 1), l.get(1)));
        i.add(Item.newInstance(9L, Product.ofNameAndVolume("Honey", 1), l.get(0)));
        i.add(Item.newInstance(10L, Product.ofNameAndVolume("Honey", 1), l.get(2)));
        i.add(Item.newInstance(11L, Product.ofNameAndVolume("Honey", 1), l.get(0)));
        i.add(Item.newInstance(12L, Product.ofNameAndVolume("Beer", 2), l.get(1)));

    }

    @Test
    void getAllTest() {
        Mockito.when(warehouseRepository.findAll()).thenReturn(l);
        List<WarehouseDTO> result = warehouseServiceImpl.getAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 5);
    }

    @Test
    void getByIdTest() {
        Mockito.when(warehouseRepository.findById(Mockito.anyLong())).thenAnswer((Answer<Optional<Warehouse>>)
                invocation -> {
                    Long arg = invocation.getArgument(0);
                    int index = arg.intValue();
                    return Optional.of(l.get(index));
                });

        WarehouseDTO result = null;
        try {
            result = warehouseServiceImpl.getByID(1L);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(), "Central WH");
    }

    @Test
    void getAllProductsOfWarehouse() {
        Mockito.when(warehouseRepository.findById(Mockito.anyLong())).thenAnswer((Answer<Optional<Warehouse>>)
                invocation -> {
                    Long arg = invocation.getArgument(0);
                    int index = arg.intValue();
                    Warehouse w = l.get(index);
                    List<Item> ii = new ArrayList<>();
                    i.forEach(l -> {
                        if (l.getWarehouse().equals(w)) {
                            ii.add(l);
                        }
                    });
                    w.setItems(ii);
                    return Optional.of(w);
                });

        List<ProductDTO> result = warehouseServiceImpl.getAllProductsOfWarehouse(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 3);
        result = warehouseServiceImpl.getAllProductsOfWarehouse(3L);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    void saveItem() {
        warehouseServiceImpl.createNew(WarehouseDTO.ofName("TMP"));
        Mockito.verify(warehouseRepository, Mockito.times(1)).save(Mockito.any());
    }


    @Test
    public void saveAllItems() {
        Mockito.when(productService.getById(Mockito.any()))
                .thenReturn(ProductDTO.newInstance(0L, "Barney", 3));
        Mockito.when(warehouseRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(l.get(0)));

        List<ItemDTO> ii = new ArrayList<>();
        ii.add(ItemDTO.newInstance(0L, 0L, 0L));
        ii.add(ItemDTO.newInstance(1L, 2L, 0L));
        ii.add(ItemDTO.newInstance(2L, 3L, 0L));
        ii.add(ItemDTO.newInstance(3L, 4L, 0L));

        warehouseServiceImpl.saveAllItems(ii, 0L);
        Mockito.verify(itemService, Mockito.times(1)).saveAll(Mockito.any());
    }
}
