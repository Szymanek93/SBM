package com.szymansky.SBM.service;


import com.szymansky.SBM.Entity.Invoice;
import com.szymansky.SBM.Entity.InvoiceDTO;
import com.szymansky.SBM.Repository.InvoiceRepository;
import com.szymansky.SBM.mapper.InvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public Optional<Invoice> save(InvoiceDTO invoiceDTO) {
        return Optional.ofNullable(invoiceDTO)
                .map(invoiceMapper::fromDTO)
                .map(invoiceRepository::save);
    }

    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findInvoiceById(Long id) {
        return invoiceRepository.findById(id);

    }
}
