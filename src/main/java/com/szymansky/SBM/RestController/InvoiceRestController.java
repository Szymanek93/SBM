package com.szymansky.SBM.RestController;


import com.szymansky.SBM.Entity.Invoice;
import com.szymansky.SBM.Entity.InvoiceDTO;
import com.szymansky.SBM.Repository.InvoiceRepository;
import com.szymansky.SBM.mapper.InvoiceMapper;
import com.szymansky.SBM.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.function.Supplier;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceRestController {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final InvoiceService invoiceService;


    @PostMapping("")
    public Invoice addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.save(invoiceDTO)
                .orElseThrow(supplyReportNotSaved());

    }

    @GetMapping("/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable Long id){
        return invoiceService.findInvoiceById(id)
                .map(invoiceMapper::toDTO)
                .orElseThrow(supplyInvoiceNotFound(id));

    }

    private Supplier<ResponseStatusException> supplyInvoiceNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Report not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }

    private Supplier<ResponseStatusException> supplyReportNotSaved() {
        return () -> {
            String message = "Something Wrong";
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
        };
    }
}
