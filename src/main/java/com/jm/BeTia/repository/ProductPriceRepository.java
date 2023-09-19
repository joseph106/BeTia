package com.jm.BeTia.repository;

import com.jm.BeTia.entity.ProductPrice;
import com.jm.BeTia.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    @Query("SELECT p FROM ProductPrice p where p.producto.sku = ?1")
    ProductPrice findBySKU(String sku);
}
