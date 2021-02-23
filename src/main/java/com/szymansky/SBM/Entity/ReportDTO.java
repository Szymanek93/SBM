package com.szymansky.SBM.Entity;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDTO {

    private Long reportId;
    private String reportCompletedTasks;
    private Long reportPointsAmount;
    private String reportOtherTasks;
//    private Long employeeId;
//    private Long businessId;
    private String employeeName;
    private String employeeLastName;
    private String businessName;

    @Temporal(TemporalType.DATE)
    private Date reportWorksDate;

    @Temporal(TemporalType.DATE)
    private Date reportDevelopmentDate;
}
