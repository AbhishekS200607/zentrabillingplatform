package com.zentra.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String businessName;
    
    private String businessAddress;
    private String businessPhone;
    private String businessEmail;
    private String logoUrl;
    private String gstNumber;
    private String upiId;
}