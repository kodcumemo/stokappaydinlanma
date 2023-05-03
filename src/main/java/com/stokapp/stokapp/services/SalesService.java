package com.stokapp.stokapp.services;

import java.util.List;

import com.stokapp.stokapp.Entities.Sales;

public interface SalesService  {
    List<Sales> getAll();
    List<Sales> salesGetAll(Long customerid);
    void salesSaved(Sales savedSales);
    void delete(Long sid);
    Sales getById(Long id);
}
