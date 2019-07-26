package ru.ler.pet.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WarehouseDTO {
    private Long warehouseID;
    private String name;
}
