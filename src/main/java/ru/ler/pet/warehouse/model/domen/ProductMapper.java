package ru.ler.pet.warehouse.model.domen;

import ru.ler.pet.warehouse.model.entity.Product;

public class ProductMapper {

    public static ProductDTO from(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getVolume());
    }

    public static Product to(ProductDTO productDTO) {
        return Product.newInstance(productDTO.getId(), productDTO.getName(), productDTO.getVolume());
    }
}
