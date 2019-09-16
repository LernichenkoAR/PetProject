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
    private Long id;
    private String name;

    public static WarehouseDTO newInstance(Long l, String name) {
        return new WarehouseDTO(l, name);
    }
}
