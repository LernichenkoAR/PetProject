package ru.ler.pet.warehouse.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by 16984608 on 24.07.2019.
 */
@Data
@NoArgsConstructor
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_WAREHOUSE")
    private Long warehouseID;
    @Column
    @Getter
    private String name;

    public Warehouse(String name) {
        this.name = name;
    }
}


