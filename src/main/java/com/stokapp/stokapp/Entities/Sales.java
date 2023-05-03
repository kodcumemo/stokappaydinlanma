package com.stokapp.stokapp.Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name = "satisyapilanurunler")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idsatisyapilan;
    private Long idMusteri;
    private Long idurun;
    @ColumnDefault("0")
    private Double satisKdvsi;
    private String satisadresi;
    @ColumnDefault("0")
    private Double satisiskonto;
    private LocalDate satistarihi;
    private Double urunAdedi;
    private String urunAdi;
    private Double urunFiyati;
    private Boolean urunkaydi;
}
