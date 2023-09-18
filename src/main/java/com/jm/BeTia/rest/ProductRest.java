package com.jm.BeTia.rest;

import com.jm.BeTia.constants.ErrorConstant;
import com.jm.BeTia.dto.ProductoDTO;
import com.jm.BeTia.entity.Producto;
import com.jm.BeTia.service.impl.ProductoServiceImpl;
import com.jm.BeTia.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRest {

    @Autowired
    private ProductoServiceImpl productoService;

    @PostMapping("/productos")
    public ResponseEntity<ProductoDTO> nuevoProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            if (productoService.validaExistSKU(productoDTO.getSku())) {
                Message message = new Message();
                ErrorConstant errorConstant = new ErrorConstant();
                message.setType(errorConstant.TIPOMENSAJEERROR);
                message.setMenssage(errorConstant.PRODUCTO_REPETIDO);
                return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(productoService.guardarProducto(productoDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/productos/upload")
    public ResponseEntity<List<String>> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            List<String> data = productoService.readExcelData(file.getInputStream());
            return ResponseEntity.ok(data);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

}
