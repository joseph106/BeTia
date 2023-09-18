package com.jm.BeTia.mapper;

import com.jm.BeTia.dto.ProductoDTO;
import com.jm.BeTia.entity.Producto;
import com.jm.BeTia.mapper.principal.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {})
public interface ProductoMapper extends EntityMapper<ProductoDTO, Producto> {
    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);
}
