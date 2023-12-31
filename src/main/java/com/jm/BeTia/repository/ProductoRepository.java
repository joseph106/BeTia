package com.jm.BeTia.repository;

import com.jm.BeTia.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

    @Query("SELECT p FROM Producto p where p.sku = ?1")
    Producto findBySKU(String sku);
}
