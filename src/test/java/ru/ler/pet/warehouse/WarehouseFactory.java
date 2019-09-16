package ru.ler.pet.warehouse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.ler.pet.warehouse.model.domen.WarehouseDTO;
import ru.ler.pet.warehouse.model.entity.Warehouse;
import ru.ler.pet.warehouse.model.request.WarehouseCreateRequest;

@NoArgsConstructor(access = AccessLevel.NONE)
public class WarehouseFactory {


    public static WarehouseCreateRequest warehouseCreateRequest(String name ){
        return new WarehouseCreateRequest(name);
    }

    public static Warehouse newWarehouse(String name){
        return Warehouse.newInstance(0L,name);
    }
    public static WarehouseDTO newWarehouseDTO(String name){
        return WarehouseDTO.newInstance(0L,name);
    }
}
