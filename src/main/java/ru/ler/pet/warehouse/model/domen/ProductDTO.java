package ru.ler.pet.warehouse.model.domen;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Data
public class ProductDTO {

    private Long id;
    private String name;
    private Integer volume;

    public static ProductDTO newInstance(Long id, String name, Integer volume) {
        return new ProductDTO(id, name, volume);
    }
}
