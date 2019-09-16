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
import ru.ler.pet.warehouse.ProductFactory;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.ProductMapper;
import ru.ler.pet.warehouse.model.entity.Product;
import ru.ler.pet.warehouse.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private static List<Product> resQuery;
    @Mock
    private ProductRepository repository;
    @InjectMocks
    private ProductServiceImpl service;

    @BeforeAll
    public static void init() {
        resQuery = new ArrayList<>();
        resQuery.add(ProductFactory.newProduct( "Barney Bears", 3));
        resQuery.add(ProductFactory.newProduct( "Peanut Butter", 1));
    }

    @Test
    void getAll() {
        Mockito.when(repository.findAll()).thenReturn(resQuery);
        Assertions.assertNotNull(service.getAll());
        Assertions.assertEquals(service.getAll().size(), 2);
    }

    @Test
    void getById() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenAnswer((Answer<Optional<Product>>) invocationOnMock -> {
            Long arg = invocationOnMock.getArgument(0);
            int index = arg.intValue();
            return Optional.of(resQuery.get(index));
        });
        ProductDTO result = null;
        try {
            result = service.findById(1L);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, ProductMapper.from(resQuery.get(1)));
    }
}