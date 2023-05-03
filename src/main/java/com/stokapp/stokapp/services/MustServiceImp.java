package com.stokapp.stokapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stokapp.stokapp.Entities.Musteri;
import com.stokapp.stokapp.repository.MustRepos;

@Service
public class MustServiceImp implements MustService{
    
    @Autowired
    MustRepos mRepos;

    @Override
    public List<Musteri> getAll() {
        List<Musteri> must =  mRepos.findAll();
        return must;
    }

    @Override
    public List<Musteri> customerGetAllDesc() {
       List<Musteri> mustadd = mRepos.findByCustomer();
       return mustadd;
    }

    @Override
    public Musteri findByMusteriId(Long id) {
        return mRepos.findById(id).get();
    }

    @Override
    public void savedCustomer(Musteri must) {
        mRepos.save(must);
    }

    @Override
    public List<Musteri> GetByFindCustomerAdress(String adress) {
    List<Musteri> findMAdress = mRepos.findByCustomerAdress(adress);
    return findMAdress;
    }

    @Override
    public List<Musteri> GetByFindCustomerName(String name) {
        List<Musteri> findMName = mRepos.FindByCustomerName(name);
        return findMName;
    }

    @Override
    public List<Musteri> GetByFindCustomerNo(String customerno) {
        List<Musteri> findMNo = mRepos.findByCustomerNo(customerno);
        return findMNo;
    }

   

}
