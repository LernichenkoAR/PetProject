package ru.ler.pet.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ler.pet.warehouse.exception.EntityNotFoundException;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.ProductDTOFactory;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor()
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public List<ProductDTO> getAll() {
        return repository.findAll().stream()
                .map(ProductDTOFactory::from)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getById(Long id) throws Throwable {
        return ProductDTOFactory.from(repository.findById(id)
                .orElseThrow((Supplier<Throwable>) () -> new EntityNotFoundException("Can not find Product")));
    }
}
