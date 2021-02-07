package com.szymansky.SBM.mapper;


import com.szymansky.SBM.Entity.Business;
import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Entity.Report;
import com.szymansky.SBM.Entity.ReportDTO;
import com.szymansky.SBM.Repository.BusinessRepository;
import com.szymansky.SBM.Repository.EmployeeRepository;
import com.szymansky.SBM.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class ReportMapper {
//    private final ReportRepository reportRepository = null;
    private final BusinessRepository businessRepository;
    private final EmployeeRepository employeeRepository;

    public ReportDTO toDTO(Report report) {
        return ReportDTO.builder()
                .reportCompletedTasks(report.getCompletedTasks())
                .reportDevelopmentDate(report.getDevelopmentDate())
                .reportWorksDate((report.getWorksDate()))
                .reportOtherTasks(report.getOtherTasks())
                .reportPointsAmount(report.getPointsAmount())
                .businessId(report.getBusiness().getId())
                .employeeId(report.getEmployee().getId())
                .reportId(report.getId())
                .build();
    }

    public Report fromDTO(ReportDTO reportDTO) {
        return Report.builder()
                .completedTasks(reportDTO.getReportCompletedTasks())
                .developmentDate(reportDTO.getReportDevelopmentDate())
                .worksDate(reportDTO.getReportWorksDate())
                .otherTasks(reportDTO.getReportOtherTasks())
                .pointsAmount(reportDTO.getReportPointsAmount())
                .business(getBusinessById(reportDTO.getBusinessId()))
                .employee(getEmployeeById(reportDTO.getEmployeeId()))
                .Id(reportDTO.getReportId())
                .build();
    }

    private Business getBusinessById(Long businessId) {
        return businessRepository.findById(businessId)
                .orElseThrow(supplyBusinessNotFound(businessId));
    }
    private Employee getEmployeeById (Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(supplyEmployeeNotFound(employeeId));
    }

    private Supplier<ResponseStatusException> supplyEmployeeNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Employee not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }

    private Supplier<ResponseStatusException> supplyBusinessNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Business not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }
}