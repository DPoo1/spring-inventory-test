package com.practice.test.controllers;

import com.practice.test.DTOs.RecordSaleRequest;
import com.practice.test.Exceptions.ProductNotFoundException;
import com.practice.test.Exceptions.StockInSufficientException;
import com.practice.test.entities.Product;
import com.practice.test.services.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping( "/add")
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        inventoryService.addProduct(product);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/sale")
    public ResponseEntity<Void> recordSale(@RequestBody RecordSaleRequest sale) {
        inventoryService.addSale(sale);
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleProductNotFound() {
        return ResponseEntity.badRequest().body(Map.of("error","Product not found"));
    }
    @ExceptionHandler(StockInSufficientException.class)
    public ResponseEntity<Map<String,String>> handleInSufficientStock() {
        return ResponseEntity.badRequest().body(Map.of("error","Stock insufficient"));
    }
}

