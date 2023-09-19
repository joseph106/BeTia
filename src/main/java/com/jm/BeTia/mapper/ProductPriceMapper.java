package com.jm.BeTia.mapper;

import com.jm.BeTia.dto.ProductPriceDTO;
import com.jm.BeTia.dto.ProductoDTO;
import com.jm.BeTia.entity.ProductPrice;
import com.jm.BeTia.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ProductPriceMapper {
    @Mapping(target = "producto.sku", source = "producto.sku")
    ProductPriceDTO toDto(ProductPrice productPrice);
}
