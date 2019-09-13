package ru.ler.pet.warehouse.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ProductServiceImplTest {

    private static List<Product> resQuery;
    @Mock
    private ProductRepository repository;
    @InjectMocks
    private ProductServiceImpl service;

    @BeforeAll
    public static void init() {
        resQuery = new ArrayList<>();
        resQuery.add(Product.newInstance(0L, "Barney Bears", 3));
        resQuery.add(Product.newInstance(0L, "Peanut Butter", 1));
    }

    @Test
    void getAll() {
        Mockito.when(repository.findAll()).thenReturn(resQuery);
        Assertions.assertNotNull(service.getAll());
        Assertions.assertEquals(service.getAll().size(), 2);
    }

    @Test
    void getById() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenAnswer(new Answer<Optional<Product>>() {
            @Override
            public Optional<Product> answer(InvocationOnMock invocationOnMock) throws Throwable {
                Long arg = invocationOnMock.getArgument(0);
                int index = arg.intValue();
                return Optional.of(resQuery.get(index));
            }
        });
        ProductDTO result = null;
        try {
            result = service.getById(1L);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, resQuery.get(1));
    }
}