package com.szymansky.SBM.mapper;


import com.szymansky.SBM.Entity.Invoice;
import com.szymansky.SBM.Entity.InvoiceDTO;
import com.szymansky.SBM.Entity.Report;
import com.szymansky.SBM.Repository.BusinessRepository;
import com.szymansky.SBM.Repository.EmployeeRepository;
import com.szymansky.SBM.Repository.InvoiceRepository;
import com.szymansky.SBM.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {
    private final InvoiceRepository invoiceRepository;
    private final ReportRepository reportRepository;
    private final BusinessRepository businessRepository;
    private final EmployeeRepository employeeRepository;


    public InvoiceDTO toDTO(Invoice invoice){

        return InvoiceDTO.builder()
                .invoiceId(invoice.getId())
                .invoicedPrice(invoice.getPrice())
                .invoicedBooked(invoice.getBooked())
                .invoicedIssued(invoice.getIssued())
                .reportEmployeeId(invoice.getReport().getEmployee().getId())
                .reportBusinessId(invoice.getReport().getBusiness().getId())
                .reportCompletedTasks(invoice.getReport().getCompletedTasks())
                .reportId(invoice.getReport().getId())
                .build();
    }

    public Invoice fromDTO(InvoiceDTO invoiceDTO){
        return Invoice.builder()

                .Id(invoiceDTO.getInvoiceId())
                .price(invoiceDTO.getInvoicedPrice())
                .booked(invoiceDTO.getInvoicedBooked())
                .issued(invoiceDTO.getInvoicedIssued())
                .report(getReportById(invoiceDTO.getReportId()))
                .build();
    }

    private Report getReportById(Long reportId) {
        return reportRepository.findById(reportId)
                .orElseThrow(supplyReportNotFound(reportId));
    }

    private Supplier<ResponseStatusException> supplyReportNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Report not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }

}
