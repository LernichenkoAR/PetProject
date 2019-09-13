package ru.ler.pet.warehouse.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Item> items;


    private Warehouse(String name) {
        this.name = name;
    }

    public Warehouse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Warehouse ofName(String name) {
        return new Warehouse(name);
    }

    public static Warehouse newInstance(Long id, String name) {
        return new Warehouse(id, name);
    }

    @Override
    public String toString() {
        return "{warehouse: {" +
                "id: " + id +
                "; name" + name +
                "} }";
    }

    public Set<Product> getUniqProducts() {
        return items.stream()
                .map(Item::getProduct)
                .collect(Collectors.toSet());

    }
}


