package ru.ler.pet.warehouse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ler.pet.warehouse.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findById(Long id);

//    Product save(Product product);
}
