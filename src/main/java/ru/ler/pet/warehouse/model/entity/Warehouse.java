package ru.ler.pet.warehouse.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings({"CanBeFinal", "unused"})
@Data
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long id;
    @SuppressWarnings("CanBeFinal")
    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    @JoinTable(
            name = "Warehouse_Product",
            joinColumns = { @JoinColumn(name = "warehouse_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<Product> products;


    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse ofName(String name) {
        return new Warehouse(name);
    }
}


