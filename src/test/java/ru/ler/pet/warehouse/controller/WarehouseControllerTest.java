package ru.ler.pet.warehouse.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.model.entity.Warehouse;
import ru.ler.pet.warehouse.service.WarehouseService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WarehouseController.class)
public class WarehouseControllerTest {

    @Autowired
    private WarehouseController controller;

    @Autowired
    private MockMvc mockMvc;
    private  static List<WarehouseDTO> l = new ArrayList<>();

    @BeforeAll
    public static void init() {
//        l.add(WarehouseDTO.ofName("O'Niel WH"));
//        l.add(WarehouseDTO.ofName("Central WH"));
//        l.add(WarehouseDTO.ofName("Fruit WH"));
//        l.add(WarehouseDTO.ofName("Drone WH"));
//        l.add(WarehouseDTO.ofName("Goods WH"));
    }

    @Test
    public void contextLoads() throws Exception{
        Assertions.assertThat(controller).isNotNull();
    }

    @MockBean
    private WarehouseService warehouseService;

    @Test
    public void getAllWarehousesReturnSuccess() throws Exception {
        this.mockMvc.perform(get("/api/v1/warehouses")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void getAllWarehousesReturnAll() throws Exception {
        Mockito.when(warehouseService.getAll()).thenReturn(l);
        this.mockMvc.perform(get("/api/v1/warehouses"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

}
