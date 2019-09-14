package ru.ler.pet.warehouse.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "product", schema = "WH_SCH")
//@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "product_id")
    private Long id;
    @Column
    private String name;
    @Column
    private Integer volume;


    private Product(Long id, String name, Integer volume) {
        this.id = id;
        this.name = name;
        this.volume = volume;
    }

    public static Product newInstance(Long id, String name, Integer volume) {
        return new Product(id, name, volume);
    }

    public static Product ofNameAndVolume(String name, Integer volume) {
        return new Product(0L, name, volume);
    }

    @Override
    public String toString() {
        return "{product: {" +
                "id: " + id +
                "; name: " + name +
                "; volume: " + volume +
                "} }";
    }

}
