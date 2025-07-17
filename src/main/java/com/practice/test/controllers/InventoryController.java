package com.practice.test.controllers;

import com.practice.test.entities.Product;
import com.practice.test.services.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;


    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        inventoryService.addProduct(product);
        return ResponseEntity.ok().build();
    }
}
