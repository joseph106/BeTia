package com.jm.BeTia.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductoDTO implements Serializable {
    private String sku;

    private String description;

    private Double price;

    private Double increment;
}
