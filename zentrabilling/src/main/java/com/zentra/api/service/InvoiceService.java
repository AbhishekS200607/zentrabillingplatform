package com.zentra.api.service;

import com.zentra.api.entity.Invoice;
import com.zentra.api.entity.InvoiceItem;
import com.zentra.api.entity.Product;
import com.zentra.api.repository.InvoiceRepository;
import com.zentra.api.repository.InvoiceItemRepository;
import com.zentra.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }
    
    public Invoice saveInvoice(Invoice invoice) {
        if (invoice.getDate() == null) {
            invoice.setDate(LocalDateTime.now());
        }
        
        if (invoice.getInvoiceNumber() == null) {
            invoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
        }
        
        return invoiceRepository.save(invoice);
    }
    
    public void deleteInvoice(Long id) {
        try {
            // Delete invoice items first using native query
            invoiceItemRepository.deleteByInvoiceId(id);
            // Then delete invoice
            invoiceRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete invoice: " + e.getMessage());
        }
    }
    
    public Invoice updateInvoiceWithInventory(Invoice invoice) {
        // Get the existing invoice to check payment status change
        Optional<Invoice> existingInvoice = invoiceRepository.findById(invoice.getId());
        
        if (existingInvoice.isPresent()) {
            Invoice existing = existingInvoice.get();
            
            // If payment status changed from UNPAID to PAID, reduce inventory
            if ("UNPAID".equals(existing.getPaymentStatus()) && "PAID".equals(invoice.getPaymentStatus())) {
                reduceInventoryForInvoice(invoice.getId());
            }
        }
        
        return invoiceRepository.save(invoice);
    }
    
    private void reduceInventoryForInvoice(Long invoiceId) {
        List<InvoiceItem> items = invoiceItemRepository.findByInvoiceId(invoiceId);
        
        for (InvoiceItem item : items) {
            Optional<Product> productOpt = productRepository.findById(item.getProductId());
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                int newStock = product.getStock() - item.getQuantity();
                product.setStock(Math.max(0, newStock)); // Don't go below 0
                productRepository.save(product);
            }
        }
    }
}