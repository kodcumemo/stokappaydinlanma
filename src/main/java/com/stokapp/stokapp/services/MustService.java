package com.stokapp.stokapp.services;

import java.util.List;

import com.stokapp.stokapp.Entities.Musteri;

public interface MustService  {
    
    List<Musteri> getAll();
    List<Musteri> customerGetAllDesc();
    List<Musteri> GetByFindCustomerAdress(String adress);
    List<Musteri> GetByFindCustomerName(String name);
    List<Musteri> GetByFindCustomerNo(String customerNo);
    Musteri findByMusteriId(Long id);
    void savedCustomer(Musteri must);
    
}
