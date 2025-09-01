package com.zentra.api.controller;

import com.zentra.api.entity.InvoiceItem;
import com.zentra.api.service.InvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/invoice-items")
public class InvoiceItemController {
    
    @Autowired
    private InvoiceItemService invoiceItemService;
    
    @PostMapping
    public InvoiceItem createInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        return invoiceItemService.saveInvoiceItem(invoiceItem);
    }
    
    @GetMapping("/invoice/{invoiceId}")
    public List<InvoiceItem> getInvoiceItems(@PathVariable Long invoiceId) {
        return invoiceItemService.getInvoiceItemsByInvoiceId(invoiceId);
    }
}