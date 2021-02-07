package com.szymansky.SBM.Entity;


import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDTO {
    private Long invoiceId;
    private Boolean invoicedIssued;
    private Boolean invoicedBooked;
    private Long invoicedPrice;
    private String reportCompletedTasks;
    private Long reportEmployeeId;
    private Long reportBusinessId;
    private Long reportId;

}
