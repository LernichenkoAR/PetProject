package ru.ler.pet.warehouse.model.domen;

import ru.ler.pet.warehouse.model.entity.Item;

public class ItemMapper {

    public static ItemDTO from(Item item) {
        return new ItemDTO(item.getId(), item.getWarehouse().getId(), item.getProduct().getId());
    }
//    public static Item to(ItemDTO itemDTO){
//        return Item.newInstance(itemDTO.getId(), itemDTO.getWarehouse(),itemDTO.getProduct());
//    }
}
