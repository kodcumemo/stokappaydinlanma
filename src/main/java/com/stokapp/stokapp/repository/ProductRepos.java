package com.stokapp.stokapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.stokapp.stokapp.Entities.Product;

@Repository
public interface ProductRepos extends JpaRepository<Product, Long> {
  // @Query("FROM Urun WHERE idurun = :idUrun AND kayitDurumu = false")
  @Query("FROM Product WHERE kayitDurumu = false")
  @Override
  List<Product> findAll();

  @Query("FROM Product ORDER BY idurun DESC")
  List<Product> findAllAdd();
  @Query("FROM Product WHERE urunadi LIKE :productName")/*AND kayitDurumu = false */
  List<Product> findByProduct(String productName);
  @Query("FROM Product ORDER BY idurun DESC")
  List<Product> findAllAddForSales();
}
