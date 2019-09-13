package ru.ler.pet.warehouse.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private Item(Long id, Product product, Warehouse warehouse) {
        this.id = id;
        this.product = product;
        this.warehouse = warehouse;
    }

    public static Item newInstance(Long id, Product product, Warehouse warehouse) {
        return new Item(id, product, warehouse);
    }

    @Override
    public String toString() {
        return "{item: {" +
                "id: " + id +
                "; product: " + product.toString() +
                "; warehouse:" + warehouse.toString() +
                "}}";
    }
}
