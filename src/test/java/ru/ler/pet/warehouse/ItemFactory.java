package ru.ler.pet.warehouse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.ler.pet.warehouse.model.domen.ItemDTO;
import ru.ler.pet.warehouse.model.entity.Item;
import ru.ler.pet.warehouse.model.entity.Product;
import ru.ler.pet.warehouse.model.entity.Warehouse;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ItemFactory {

    public static Item newItem(Product product, Warehouse warehouse){
        return Item.newInstance(null, product, warehouse);
    }
    public static ItemDTO newItemDTO(Long product, Long warehouse){
        return ItemDTO.newInstance(null, product, warehouse);
    }
}
