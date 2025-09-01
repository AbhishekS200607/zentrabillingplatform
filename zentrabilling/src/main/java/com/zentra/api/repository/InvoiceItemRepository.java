package com.zentra.api.repository;

import com.zentra.api.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
    List<InvoiceItem> findByInvoiceId(Long invoiceId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM InvoiceItem i WHERE i.invoiceId = ?1")
    void deleteByInvoiceId(Long invoiceId);
}