package com.jm.BeTia.service.declare;

import com.jm.BeTia.dto.ProductoDTO;
import com.jm.BeTia.entity.Producto;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ProductoService {
    ProductoDTO guardarProducto(ProductoDTO productoDTO);

    Boolean validaExistSKU(String sku);

    List<String> readExcelData(InputStream inputStream) throws IOException;
}
