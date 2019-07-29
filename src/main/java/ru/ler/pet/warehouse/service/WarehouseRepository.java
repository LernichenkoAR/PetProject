package ru.ler.pet.warehouse.service;

import org.springframework.data.repository.CrudRepository;
import ru.ler.pet.warehouse.model.entity.Warehouse;

import java.util.List;
import java.util.Optional;

/**
 * Created by 16984608 on 24.07.2019.
 */
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

    List<Warehouse> findByName(String Name);

    List<Warehouse> findAll();
    Optional<Warehouse> findById(Long id);



}
