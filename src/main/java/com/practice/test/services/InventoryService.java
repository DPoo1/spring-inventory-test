package com.practice.test.services;

import com.practice.test.DTOs.RecordSaleRequest;
import com.practice.test.Exceptions.ProductNotFoundException;
import com.practice.test.Exceptions.StockInSufficientException;
import com.practice.test.Repo.ProductRepository;
import com.practice.test.Repo.SaleRepository;
import com.practice.test.entities.Product;
import com.practice.test.entities.Sale;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public void addProduct(Product product) {
        var p = productRepository.findById(product.getId()).orElse(null);
        if(p==null)productRepository.save(product);
        else {
            p.setStock(p.getStock()+ product.getStock());
            productRepository.save(p);
        }
    }

    public void addSale(RecordSaleRequest request) {
        var p = productRepository.findById(request.getProductId()).orElse(null);
        if(p==null)throw new ProductNotFoundException();
        if(request.getQuantitySold()>p.getStock()) throw new StockInSufficientException();
        p.setStock(p.getStock()- request.getQuantitySold());
        productRepository.save(p);
        var sale=new Sale();
        sale.setProduct(p);
        sale.setQuantitySold(request.getQuantitySold());
        saleRepository.save(sale);
    }
}
