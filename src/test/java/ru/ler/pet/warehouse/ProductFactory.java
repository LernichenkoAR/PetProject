package ru.ler.pet.warehouse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.entity.Product;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ProductFactory {

    public static Product newProduct(String name, Integer volume){
        return Product.newInstance(null, name, volume);
    }
    public static ProductDTO newProductDTO(String name, Integer volume){
        return ProductDTO.newInstance(null, name, volume);
    }

}
