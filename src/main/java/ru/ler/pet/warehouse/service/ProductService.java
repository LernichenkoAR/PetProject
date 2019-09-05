package ru.ler.pet.warehouse.service;

import ru.ler.pet.warehouse.model.domen.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAll();
    ProductDTO getById(Long id) throws Throwable;
}
