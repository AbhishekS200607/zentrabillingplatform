package com.zentra.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String category;
    
    @Column(unique = true)
    private String barcode;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costPrice;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sellingPrice;
    
    @Column(nullable = false)
    private Integer stock = 0;
    
    @Column(nullable = false)
    private Integer threshold = 10;
    
    public Integer getLowStockThreshold() {
        return threshold;
    }
}