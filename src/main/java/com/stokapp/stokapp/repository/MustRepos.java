package com.stokapp.stokapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import com.stokapp.stokapp.Entities.Musteri;

@Controller
public interface MustRepos extends JpaRepository<Musteri,Long> {
    @Query("FROM Musteri where kayitdurumu = false ORDER BY idMusteri DESC")
    @Override
    List<Musteri> findAll();

    @Query("FROM Musteri where kayitdurumu = false ORDER BY idMusteri DESC")
    List<Musteri> findByCustomer();

    @Query("FROM Musteri WHERE adres LIKE :adress AND kayitdurumu = false ORDER BY idMusteri DESC")
    List<Musteri> findByCustomerAdress(String adress);
    @Query("FROM Musteri WHERE isim LIKE :name AND kayitdurumu = false ORDER BY idMusteri DESC")
    List<Musteri> FindByCustomerName(String name);
    @Query("FROM Musteri WHERE musterino LIKE :customerNo AND kayitdurumu = false ORDER BY idMusteri DESC")
    List<Musteri> findByCustomerNo(String customerNo);
    
}
