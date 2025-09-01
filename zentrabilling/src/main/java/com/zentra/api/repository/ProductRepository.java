package com.zentra.api.repository;

import com.zentra.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByBarcode(String barcode);
    
    @Query("SELECT p FROM Product p WHERE p.stock <= 10")
    List<Product> findLowStockProducts();
    
    List<Product> findByNameContainingIgnoreCaseOrBarcodeContaining(String name, String barcode);
}