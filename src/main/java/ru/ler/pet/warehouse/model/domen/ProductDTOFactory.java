package ru.ler.pet.warehouse.model.domen;

import ru.ler.pet.warehouse.model.entity.Product;

public class ProductDTOFactory {

    public static ProductDTO from (Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getVolume());
    }
}
