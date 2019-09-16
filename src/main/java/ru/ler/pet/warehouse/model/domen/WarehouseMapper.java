package ru.ler.pet.warehouse.model.domen;

import ru.ler.pet.warehouse.model.entity.Warehouse;
import ru.ler.pet.warehouse.model.request.WarehouseCreateRequest;

public class WarehouseMapper {

    public static WarehouseDTO from(Warehouse warehouse) {
        return new WarehouseDTO(warehouse.getId(), warehouse.getName());
    }

    public static Warehouse to(WarehouseDTO warehouseDTO) {
        return Warehouse.newInstance(warehouseDTO.getId(), warehouseDTO.getName());
    }
    public static Warehouse to(WarehouseCreateRequest warehouse) {
        return Warehouse.newInstance(null, warehouse.getName());
    }

}
