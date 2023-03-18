package com.example.demo.shared;

import com.example.demo.model.Product;

public class ProductValidator {
    public static void check(final Product product) {
        final int price = product.getPrice();
        if (price < 100 || price > 10_000) {
//            throw new IllegalStateException("Invalid price");
        }
    }
}
