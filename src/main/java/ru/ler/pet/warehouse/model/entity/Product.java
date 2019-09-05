package ru.ler.pet.warehouse.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column
    private String name;
    @Column
    private Integer volume;
    @ManyToMany(mappedBy = "products")
    private List<Warehouse> warehouses;

    private Product(Long id, String name, Integer volume) {
        this.id = id;
        this.name = name;
        this.volume = volume;
    }

    public static Product newInstanse(String name, Integer volume){
        return new Product(0L, name, volume);
    }
}
