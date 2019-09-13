package ru.ler.pet.warehouse.model.domen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Data
public class ItemDTO {

    private Long id;
    private Long product;
    private Long warehouse;

    public static ItemDTO newInstance(Long id, Long product, Long warehouse) {
        return new ItemDTO(id, product, warehouse);
    }
}
