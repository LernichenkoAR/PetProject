package ru.ler.pet.warehouse.model.request;


import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class ProductCreateRequest {

    private String name;
    private Integer volume;
}
