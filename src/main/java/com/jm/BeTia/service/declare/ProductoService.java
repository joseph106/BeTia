package com.jm.BeTia.service.declare;

import com.jm.BeTia.dto.ProductoDTO;

import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductoService {
    ProductoDTO guardarProducto(ProductoDTO productoDTO);

    Boolean validaExistSKU(String sku);

    List<String> readExcelData(InputStream inputStream) throws IOException;

    Page<ProductoDTO> getProductos(Pageable pageable);

    ProductoDTO getProductBySKU(String sku);


}
