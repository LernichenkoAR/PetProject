package ru.ler.pet.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ler.pet.warehouse.model.domen.ProductDTO;
import ru.ler.pet.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService service;

    @GetMapping("/products")
    public List<ProductDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/products/{product_id}")
    public ProductDTO getById(@PathVariable("product_id") Long id) throws Throwable {
        return service.getById(id);
    }

    @PostMapping("/products/")
    public void createNew(@RequestBody ProductDTO product) {
        service.save(product);
    }

}
