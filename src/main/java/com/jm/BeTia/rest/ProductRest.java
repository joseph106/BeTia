package com.jm.BeTia.rest;

import com.jm.BeTia.constants.ErrorConstant;
import com.jm.BeTia.dto.ProductPriceDTO;
import com.jm.BeTia.dto.ProductoDTO;
import com.jm.BeTia.service.impl.ProductPriceServiceImpl;
import com.jm.BeTia.service.impl.ProductoServiceImpl;
import com.jm.BeTia.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRest {

    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired
    private ProductPriceServiceImpl productPriceService;

    @PostMapping("/product")
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

    @PostMapping("/product/upload")
    public ResponseEntity<List<String>> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            List<String> data = productoService.readExcelData(file.getInputStream());
            return ResponseEntity.ok(data);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductoDTO>> getAllP0rodicts(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size){
        try {
            Pageable pageable = PageRequest.of(page, size);
            return new ResponseEntity(productoService.getProductos(pageable), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/product/{sku}")
    private ResponseEntity<ProductoDTO> getProductBySKU(@PathVariable String sku){
        try {
            return ResponseEntity.ok(productoService.getProductBySKU(sku));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping("/product_credit/{sku}")
    private  ResponseEntity<List<ProductPriceDTO>> getPoductByCreditSKU(@PathVariable String sku, @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "5") int size){
        try {
            if (productPriceService.getProductBySKUCredit(sku) != null){
                return ResponseEntity.ok(Collections.singletonList(productPriceService.getProductBySKUCredit(sku)));
            }
            else {
                Pageable pageable = PageRequest.of(page, size);
                return new ResponseEntity(productPriceService.getAllCreditProduct(pageable), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
