package com.practice.test.services;

import com.practice.test.Repo.ProductRepository;
import com.practice.test.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
