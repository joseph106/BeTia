package com.jm.BeTia.service.declare;

import com.jm.BeTia.dto.ProductPriceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductPriceService {
    ProductPriceDTO getProductBySKUCredit(String sku);

    Page<ProductPriceDTO> getAllCreditProduct(Pageable pageable);
}
