package ru.ler.pet.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ler.pet.warehouse.exception.BadRequestException;
import ru.ler.pet.warehouse.exception.EntityNotFoundException;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.model.domen.ProductMapper;
import ru.ler.pet.warehouse.model.request.ProductCreateRequest;
import ru.ler.pet.warehouse.repository.ProductRepository;

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
                .map(ProductMapper::from)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        if (id < 0) {
            throw new BadRequestException();
        }
        return ProductMapper.from(repository.findById(id)
                .orElseThrow((Supplier<RuntimeException>) () -> new EntityNotFoundException("Can not find Product")));
    }

    @Override
    public ProductDTO save(ProductCreateRequest product) {
        return ProductMapper.from(repository.save(ProductMapper.to(product)));
    }
}
