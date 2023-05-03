package com.stokapp.stokapp.services;

import java.util.List;

import com.stokapp.stokapp.Entities.Product;

public interface ProductService {
    List<Product> ProductGetAll();
    void saved(Product product);

    List<Product> ProductGetAllDesc();
    List<Product> ProductGetAllDescForSales();
    Product findByProductId(Long id);
    List<Product> GetByFindProduct(String name);
}
