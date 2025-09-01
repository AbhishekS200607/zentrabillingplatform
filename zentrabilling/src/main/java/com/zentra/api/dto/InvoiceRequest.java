package com.zentra.api.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class InvoiceRequest {
    private Long customerId;
    private BigDecimal taxRate;
    private List<InvoiceItemRequest> items;
    
    @Data
    public static class InvoiceItemRequest {
        private Long productId;
        private Integer quantity;
    }
}