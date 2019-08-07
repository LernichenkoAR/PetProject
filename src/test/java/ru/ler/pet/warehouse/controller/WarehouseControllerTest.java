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
import ru.ler.pet.warehouse.exception.WarehouseNotFoundException;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.service.WarehouseServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("unused")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WarehouseController.class)
class WarehouseControllerTest {

    private static final List<WarehouseDTO> l = new ArrayList<>();
    @Autowired
    private WarehouseController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WarehouseServiceImpl warehouseServiceImpl;

    @BeforeAll
    static void init() {
        l.add(WarehouseDTO.ofName("O'Niel WH"));
        l.add(WarehouseDTO.ofName("Central WH"));
        l.add(WarehouseDTO.ofName("Fruit WH"));
        l.add(WarehouseDTO.ofName("Drone WH"));
        l.add(WarehouseDTO.ofName("Goods WH"));
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(controller).isNotNull();
    }

    @Test
    void getAllWarehousesReturnSuccess() throws Exception {
        this.mockMvc.perform(get("/api/v1/warehouses"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllWarehousesReturnAll() throws Exception {
        Mockito.when(warehouseServiceImpl.getAll()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/api/v1/warehouses"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

    @Test
    void getAllWarehousesCorrectNum() throws Exception {
        Mockito.when(warehouseServiceImpl.getAll()).thenReturn(l);
        this.mockMvc.perform(get("/api/v1/warehouses"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    final ObjectMapper mapper = new ObjectMapper();
                    List<WarehouseDTO> whs = mapper.readValue(mvcResult.getResponse().getContentAsString(),
                            new TypeReference<List<WarehouseDTO>>() {
                            });
                    if (whs.size() != 5) {
                        throw new RuntimeException();
                    }
                });
    }


    @Test
    void getWarehouseByIdSuccess() throws Exception {
        Mockito.when(warehouseServiceImpl.getWarehouseByID(Mockito.anyLong())).then(invocationOnMock -> {
            Long lo = invocationOnMock.getArgument(0);
            if (lo < 0) {
                throw new BadRequestException();
            }
            if (lo >= l.size()) {
                throw new WarehouseNotFoundException();
            }
            return l.get(lo.intValue());
        });
        this.mockMvc.perform(get("/api/v1/warehouses/1"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    final ObjectMapper mapper = new ObjectMapper();
                    mapper.readTree(mvcResult.getResponse().getContentAsString());
                })
                .andExpect(content().string(containsString("Central WH")));
    }


    @Test
    void getWarehouseByIdNotFound() throws Exception {
        Mockito.when(warehouseServiceImpl.getWarehouseByID(Mockito.anyLong())).then(invocationOnMock -> {
            Long lo = invocationOnMock.getArgument(0);
            if (lo < 0) {
                throw new BadRequestException();
            }
            if (lo >= l.size()) {
                throw new WarehouseNotFoundException();
            }
            return l.get(lo.intValue());
        });
        this.mockMvc.perform(get("/api/v1/warehouses/7"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getWarehouseByIdBadRequest() throws Exception {
        Mockito.when(warehouseServiceImpl.getWarehouseByID(Mockito.anyLong())).then(invocationOnMock -> {
            Long lo = invocationOnMock.getArgument(0);
            if (lo >= l.size()) {
                return null;
            }
            return l.get(lo.intValue());
        });
        this.mockMvc.perform(get("/api/v1/warehouses/-1"))
                .andExpect(status().isBadRequest());
    }
}
