package com.example.demo.controller;

import com.example.demo.dto.PriceUpdate;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable final String id) {
        Objects.requireNonNull(id);
        return productService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found product with id " + id));
    }

    @PostMapping
    public String create(@RequestBody @Valid final Product product, final BindingResult result) {
        if (product.getPrice() == 0) {
            product.setPrice(100);
        }
        return productService.save(product);
    }

    @PostMapping("/random-product")
    public String createRandomProduct() {
        return productService.createRandomProduct();
    }

    @PutMapping("/{id}")
    public void updatePrice(@RequestBody PriceUpdate priceUpdate, @PathVariable String id) {
        if (priceUpdate.getPrice() != 0) {
            productService.updatePrice(id, priceUpdate.getPrice());
        }
    }

    @DeleteMapping()
    public void delete(@RequestParam String name) {
        productService.deleteByName(name);
    }
}
