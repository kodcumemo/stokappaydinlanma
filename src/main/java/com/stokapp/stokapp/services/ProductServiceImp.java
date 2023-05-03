package com.stokapp.stokapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stokapp.stokapp.Entities.Product;
import com.stokapp.stokapp.repository.ProductRepos;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    ProductRepos pRepos;

    @Override
    public List<Product> ProductGetAll() {
        List<Product> product = pRepos.findAll();
        return product;
    }

    @Override
    public void saved(Product product) {
        pRepos.save(product);
    }

    @Override
    public List<Product> ProductGetAllDesc() {
        List<Product> product = pRepos.findAllAdd();
        return product;
    }

    @Override
    public List<Product> GetByFindProduct(String name) {
        List<Product> product = pRepos.findByProduct(name);
        return product;
    }

    @Override
    public List<Product> ProductGetAllDescForSales() {
        List<Product> product = pRepos.findAllAddForSales();
        return product;    
    }

    @Override
    public Product findByProductId(Long id) {
       return pRepos.findById(id).orElse(null); // buraya tabloda olmayan bir id nin gelmemesi lazım
    }
    
}
