package com.stokapp.stokapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stokapp.stokapp.Entities.Sales;

@Repository
public interface SalesRepos extends JpaRepository<Sales, Long>{
    @Query("FROM Sales WHERE urunkaydi = false ORDER BY idsatisyapilan DESC")
    List<Sales> findAll();
    @Query("FROM Sales WHERE idMusteri = :customerid AND urunkaydi = false ORDER BY idsatisyapilan DESC")
    List<Sales> findAllSales(Long customerid);
}
