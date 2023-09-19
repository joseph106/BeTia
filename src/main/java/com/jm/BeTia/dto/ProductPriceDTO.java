package com.jm.BeTia.dto;

import com.jm.BeTia.entity.Producto;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductPriceDTO implements Serializable {
    private Long id;
    private Double incrementedPrice;
    private Double MaxDuesNo;
    private Producto producto;
}
