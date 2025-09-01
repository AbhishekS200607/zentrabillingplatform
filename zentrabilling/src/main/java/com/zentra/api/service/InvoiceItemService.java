package com.zentra.api.service;

import com.zentra.api.entity.InvoiceItem;
import com.zentra.api.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InvoiceItemService {
    
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;
    
    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemRepository.save(invoiceItem);
    }
    
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(Long invoiceId) {
        return invoiceItemRepository.findByInvoiceId(invoiceId);
    }
    
    @Transactional
    public void deleteByInvoiceId(Long invoiceId) {
        invoiceItemRepository.deleteByInvoiceId(invoiceId);
    }
}