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
import ru.ler.pet.warehouse.ItemFactory;
import ru.ler.pet.warehouse.ProductFactory;
import ru.ler.pet.warehouse.WarehouseFactory;
import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.model.entity.Item;
import ru.ler.pet.warehouse.model.entity.Warehouse;
import ru.ler.pet.warehouse.repository.WarehouseRepository;

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
        l.add(WarehouseFactory.newWarehouse( "O'Niel WH"));
        l.add(WarehouseFactory.newWarehouse("Central WH"));
        l.add(WarehouseFactory.newWarehouse( "Fruit WH"));
        l.add(WarehouseFactory.newWarehouse("Drone WH"));
        l.add(WarehouseFactory.newWarehouse("Goods WH"));

        i.add(ItemFactory.newItem( ProductFactory.newProduct("Barney Bear", 3), l.get(0)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Barney Bear", 3), l.get(0)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Barney Bear", 3), l.get(0)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Barney Bear", 3), l.get(1)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Barney Bear", 3), l.get(1)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Barney Bear", 3), l.get(1)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Honey", 1), l.get(0)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Honey", 1), l.get(2)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Honey", 1), l.get(1)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Honey", 1), l.get(0)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Honey", 1), l.get(2)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Honey", 1), l.get(0)));
        i.add(ItemFactory.newItem( ProductFactory.newProduct("Beer", 2), l.get(1)));

    }

    @Test
    void getAllTest() {
        Mockito.when(warehouseRepository.findAll()).thenReturn(l);
        List<WarehouseDTO> result = warehouseServiceImpl.findAll();
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
            result = warehouseServiceImpl.findById(1L);
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

        List<ProductDTO> result = warehouseServiceImpl.findAllProductsOfWarehouse(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 3);
        result = warehouseServiceImpl.findAllProductsOfWarehouse(3L);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    void saveWarehouse() {
        WarehouseDTO warehouseDTO = warehouseServiceImpl.save(WarehouseFactory.warehouseCreateRequest("TMP"));
        Mockito.verify(warehouseRepository, Mockito.times(1)).save(Mockito.any());
        Assert.assertNotNull(warehouseDTO);
        Assert.assertEquals(warehouseDTO.getName(), "TMP");
    }


    @Test
    public void loadProduct() {
        Mockito.when(productService.findById(Mockito.any()))
                .thenReturn(ProductFactory.newProductDTO( "Barney", 3));
        Mockito.when(warehouseRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(l.get(0)));

        List<ItemDTO> items = warehouseServiceImpl.loadProduct(0L, 0L,10);

        Mockito.verify(itemService, Mockito.times(1)).saveAll(Mockito.any());
        Assert.assertNotNull(items);
        Assert.assertEquals(items.size(), 10);
    }
}
