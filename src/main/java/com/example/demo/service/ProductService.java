package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.shared.ProductCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductCreator productCreator;

    @Autowired
    ProductService(final ProductRepository productRepository, final ProductCreator productCreator) {
        this.productRepository = productRepository;
        this.productCreator = productCreator;
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(final String id) {
        return productRepository.findById(id);
    }

    public String save(final Product product) {
        return productRepository.save(product).getId();
    }

    public String createRandomProduct() {
        final Product product = productCreator.create();
        return productRepository.save(product).getId();
    }

    public void updatePrice(final String id, final int price) {
        productRepository.findById(id)
                .ifPresent(product -> {
                    product.setPrice(price);
                    productRepository.save(product);
                });
    }

    @Transactional
    public void deleteByName(final String name) {
        productRepository.deleteByNameIgnoreCase(name);
    }
}
