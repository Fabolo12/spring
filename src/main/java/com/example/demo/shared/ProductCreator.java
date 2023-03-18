package com.example.demo.shared;

import com.example.demo.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductCreator {
    public Product create() {
        final Product product = new Product();
        product.setPrice(150);
        product.setName("Random product");
        return product;
    }
}
