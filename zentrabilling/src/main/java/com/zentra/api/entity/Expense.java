package com.zentra.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    private String category;
    
    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();
    
    public String getExpenseDate() {
        return date != null ? date.toLocalDate().toString() : null;
    }
}