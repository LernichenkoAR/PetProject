package ru.ler.pet.warehouse.model.domen;

import ru.ler.pet.warehouse.model.entity.Warehouse;

public class WarehouseDTOFactory {

    public static WarehouseDTO from(Warehouse warehouse) {
        return new WarehouseDTO(warehouse.getId(), warehouse.getName());
    }
}
