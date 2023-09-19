package com.jm.BeTia.service.impl;

import com.jm.BeTia.constants.ErrorConstant;
import com.jm.BeTia.dto.ProductoDTO;
import com.jm.BeTia.entity.Producto;
import com.jm.BeTia.mapper.ProductoMapper;
import com.jm.BeTia.repository.ProductoRepository;
import com.jm.BeTia.service.declare.ProductoService;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    Logger logger = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Override
    public ProductoDTO guardarProducto(ProductoDTO productoDTO) {
        try {
            Producto nuevoProducto = productoMapper.toEntity(productoDTO);

            return productoMapper.toDto(productoRepository.save(nuevoProducto));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean validaExistSKU(String sku) {
        try {
            if (productoRepository.existsById(sku)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<String> readExcelData(InputStream inputStream) {
        try {
            List<String> data = new ArrayList<>();

            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            ProductoDTO productoDTO;

            int skipRows = 1;

            for (Row row : sheet) {
                /* Ignoramos la primera fila ya que pertenece a los nombres de la aplicacion  */
                if (skipRows > 0) {
                    skipRows--;
                    continue;
                }
                productoDTO = new ProductoDTO();
                productoDTO.setSku(row.getCell(0).getStringCellValue());
                productoDTO.setDescription(row.getCell(1).getStringCellValue());
                productoDTO.setPrice(row.getCell(2).getNumericCellValue());
                productoDTO.setIncrement(row.getCell(3).getNumericCellValue());

                if (productoRepository.existsById(productoDTO.getSku())){
                    System.err.println(ErrorConstant.PRODUCTO_REPETIDO);
                } else {
                    productoRepository.save(productoMapper.toEntity(productoDTO));
                }
            }
            workbook.close();
            return data;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<ProductoDTO> getProductos(Pageable pageable){
        try {
            Page<Producto> productos = productoRepository.findAll(pageable);
            return productos.map(productoMapper::toDto);
        } catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ProductoDTO getProductBySKU(String sku) {
        try {
            return productoMapper.toDto(productoRepository.findBySKU(sku));
        } catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

}
