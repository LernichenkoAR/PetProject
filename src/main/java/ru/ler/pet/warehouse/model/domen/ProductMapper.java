package ru.ler.pet.warehouse.model.domen;

import ru.ler.pet.warehouse.model.entity.Product;
import ru.ler.pet.warehouse.model.request.ProductCreateRequest;

public class ProductMapper {

    public static ProductDTO from(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getVolume());
    }

    public static Product to(ProductDTO productDTO) {
        return Product.newInstance(productDTO.getId(), productDTO.getName(), productDTO.getVolume());
    }
    public static Product to(ProductCreateRequest product) {
        return Product.newInstance(null, product.getName(), product.getVolume());
    }
}
