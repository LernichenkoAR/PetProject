package ru.ler.pet.warehouse.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings({"CanBeFinal", "unused"})
@Data
@NoArgsConstructor
@Entity
@Table(name = "warehouse", schema = "WH_SCH")
//@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;
    @SuppressWarnings("CanBeFinal")
    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL,
            mappedBy = "warehouse")
    private List<Item> items = new ArrayList<>();


    private Warehouse(String name) {
        this.name = name;
    }

    private Warehouse(Long id, String name) {
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
        if (items != null && items.size() != 0) {
            return items.stream()
                    .map(Item::getProduct)
                    .collect(Collectors.toSet());
        }
        return new HashSet<>();

    }
}


