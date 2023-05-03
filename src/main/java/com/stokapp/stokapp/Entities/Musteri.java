package com.stokapp.stokapp.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data 
@Table(name = "musteri")
public class Musteri {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMusteri;
    private String isim; 
    private String adres;
    @Column(nullable = false)
    private Boolean kayitdurumu;
    private String musterino;
}
