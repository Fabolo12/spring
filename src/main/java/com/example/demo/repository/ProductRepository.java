package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    @Modifying
    void deleteByNameIgnoreCase(final String name);
}
