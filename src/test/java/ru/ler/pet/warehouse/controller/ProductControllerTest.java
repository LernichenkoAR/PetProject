package ru.ler.pet.warehouse.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.ler.pet.warehouse.exception.BadRequestException;
import ru.ler.pet.warehouse.exception.EntityNotFoundException;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.service.ProductServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    private static final List<ProductDTO> l = new ArrayList<>();
    @Autowired
    private ProductController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductServiceImpl productServiceImpl;

    @BeforeAll
    static void init() {
        l.add(ProductDTO.ofNameAndVolume("Barney Bear", 3));
        l.add(ProductDTO.ofNameAndVolume("Honey", 2));
        l.add(ProductDTO.ofNameAndVolume("Milk", 5));
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(controller).isNotNull();
    }

    @Test
    void fetAllProductsReturnSuccess() throws Exception {
        this.mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllProductsReturnAll() throws Exception {
        Mockito.when(productServiceImpl.getAll()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

    @Test
    void getAllProductsCorrectNum() throws Exception {
        Mockito.when(productServiceImpl.getAll()).thenReturn(l);
        this.mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    final ObjectMapper mapper = new ObjectMapper();
                    List<ProductDTO> whs = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                            new TypeReference<List<ProductDTO>>() {
                            });
                    if (whs.size() != 3) {
                        throw new RuntimeException();
                    }
                });
    }


    @Test
    void getWarehouseByIdSuccess() throws Throwable {
        Mockito.when(productServiceImpl.getById(Mockito.anyLong())).then(invocationOnMock -> {
            Long lo = invocationOnMock.getArgument(0);
            if (lo < 0) {
                throw new BadRequestException();
            }
            if (lo >= l.size()) {
                throw new EntityNotFoundException();
            }
            return l.get(lo.intValue());
        });
        this.mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    final ObjectMapper mapper = new ObjectMapper();
                    mapper.readTree(mvcResult.getResponse().getContentAsString());
                })
                .andExpect(content().string(containsString("Honey")));
    }


    @Test
    void getWarehouseByIdNotFound() throws Throwable {
        Mockito.when(productServiceImpl.getById(Mockito.anyLong())).then(invocationOnMock -> {
            Long lo = invocationOnMock.getArgument(0);
            if (lo < 0) {
                throw new BadRequestException();
            }
            if (lo >= l.size()) {
                throw new EntityNotFoundException();
            }
            return l.get(lo.intValue());
        });
        this.mockMvc.perform(get("/api/v1/products/7"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getWarehouseByIdBadRequest() throws Throwable {
        Mockito.when(productServiceImpl.getById(Mockito.anyLong())).then(invocationOnMock -> {
            Long lo = invocationOnMock.getArgument(0);
            if (lo < 0) {
                throw new BadRequestException();
            }
            if (lo >= l.size()) {
                return null;
            }
            return l.get(lo.intValue());
        });
        this.mockMvc.perform(get("/api/v1/products/-1"))
                .andExpect(status().isBadRequest());
    }
}