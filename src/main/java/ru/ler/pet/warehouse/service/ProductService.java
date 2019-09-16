package ru.ler.pet.warehouse.service;

import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.request.ProductCreateRequest;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAll();

    ProductDTO findById(Long id);

    ProductDTO save(ProductCreateRequest product);

}
