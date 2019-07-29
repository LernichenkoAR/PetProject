package ru.ler.pet.warehouse.model.domen;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class WarehouseDTO {
    private final Long warehouseID;
    private final String name;

    public WarehouseDTO(Long warehouseID, String name) {
        this.warehouseID = warehouseID;
        this.name = name;
    }

    public static WarehouseDTO ofName(String name){
        return new WarehouseDTO(0L, name);

    }
}
