package com.stokapp.stokapp.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "urun")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idurun;
    private String urunadi;
    private String faturano;
    private Double tutar;
    private Double adedi;
    
    private Boolean kayitDurumu;
    private Double satisFiyati;
    private Long idMusteri;
    private Long idUMusteri;
    private Double satisiskonto;
    private Double satisKdvsi;
    private String satisadresi;
    
}
