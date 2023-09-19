package com.jm.BeTia.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "productPrice")
public class ProductPrice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IncrementedPrice")
    private Double incrementedPrice;

    @Column(name = "MaxDuesNo")
    private Double MaxDuesNo;

    @OneToOne
    @JoinColumn(name = "sku")
    private Producto producto;
}
