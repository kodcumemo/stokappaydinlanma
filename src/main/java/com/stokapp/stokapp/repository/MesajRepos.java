package com.stokapp.stokapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stokapp.stokapp.Entities.Mesajlar;

public interface MesajRepos extends JpaRepository<Mesajlar,Long>{
    @Override
    List<Mesajlar> findAll();
}
