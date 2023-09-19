package com.jm.BeTia.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Entity
@Table(name = "product")
@Data
public class Producto implements Serializable {

    @Id
    @Column(unique = true, nullable = false, name = "sku", length = 9)
    private String sku;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "increment")
    private Double increment;

}
