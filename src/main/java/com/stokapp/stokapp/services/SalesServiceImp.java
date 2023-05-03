package com.stokapp.stokapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stokapp.stokapp.Entities.Sales;
import com.stokapp.stokapp.repository.SalesRepos;
@Service
public class SalesServiceImp implements SalesService {

    @Autowired
    SalesRepos sRepos;
    
    @Override
    public List<Sales> getAll() {
        List<Sales> sales = sRepos.findAll();
        return sales;
    }

    @Override
    public List<Sales> salesGetAll(Long customerid) {
       List<Sales> sales = sRepos.findAllSales(customerid);
    return sales;
    }

    @Override
    public void salesSaved(Sales savedSales) {
        sRepos.save(savedSales);
    }

    @Override
    public void delete(Long sid) {
        sRepos.deleteById(sid);
    }

    @Override
    public Sales getById(Long id) {
        return sRepos.findById(id).get();
    }
}
