package ru.ler.pet.warehouse.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SuppressWarnings({"CanBeFinal", "unused"})
@Data
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @SuppressWarnings("CanBeFinal")
    @Column
    private String name;

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse ofName(String name) {
        return new Warehouse(name);
    }
}


