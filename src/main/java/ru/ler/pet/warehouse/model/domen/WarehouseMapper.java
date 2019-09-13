package ru.ler.pet.warehouse.model.domen;

import ru.ler.pet.warehouse.model.entity.Warehouse;

public class WarehouseMapper {

    public static WarehouseDTO from(Warehouse warehouse) {
        return new WarehouseDTO(warehouse.getId(), warehouse.getName());
    }

    public static Warehouse to(WarehouseDTO warehouseDTO) {
        return Warehouse.newInstance(warehouseDTO.getId(), warehouseDTO.getName());
    }
}
