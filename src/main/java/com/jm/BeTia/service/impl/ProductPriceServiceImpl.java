package com.jm.BeTia.service.impl;

import com.jm.BeTia.dto.ProductPriceDTO;
import com.jm.BeTia.entity.ProductPrice;
import com.jm.BeTia.mapper.ProductPriceMapper;
import com.jm.BeTia.repository.ProductPriceRepository;
import com.jm.BeTia.service.declare.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
    @Autowired
    private ProductPriceMapper productPriceMapper;

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Override
    public ProductPriceDTO getProductBySKUCredit(String sku) {
        try {
            return productPriceMapper.toDto(productPriceRepository.findBySKU(sku));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<ProductPriceDTO> getAllCreditProduct(Pageable pageable) {
        try {

            Page<ProductPrice> productosprice = productPriceRepository.findAll(pageable);
            return productosprice.map(productPriceMapper::toDto);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
