package ru.ler.pet.warehouse.model.domen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@SuppressWarnings("unused")
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Data
public class WarehouseDTO {
    private Long warehouseID;
    private String name;


    public static WarehouseDTO ofName(String name) {
        return new WarehouseDTO(0L, name);

    }
}
