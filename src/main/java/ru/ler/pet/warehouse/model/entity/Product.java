package ru.ler.pet.warehouse.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    static Product newInstanse(String name, Integer volume){
        return new Product(0L, name, volume);
    }
}
