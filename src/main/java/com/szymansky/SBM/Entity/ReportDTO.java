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

    private Long Id;
    private String reportCompleteTasks;
    @Temporal(TemporalType.DATE)
    private Date reportWorksDate;
    @Temporal(TemporalType.DATE)
    private Date reportDevelopmentDate;
    private Long reportPointsAmount;
    private String reportOtherTasks;
    private Long employeeId;
    private Long businessId;
}
